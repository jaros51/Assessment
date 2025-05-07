package sk.wm.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@Configuration
@EnableAsync
public class ClientApplication {

    private static final String FRANKENSTEIN_URL = "http://localhost:8081";
    private static final String DRACULA_URL = "http://localhost:8082";
    private static final ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        // Start threads to process each server concurrently
        Thread frankensteinThread = new Thread(() -> processServer(FRANKENSTEIN_URL));
        Thread draculaThread = new Thread(() -> processServer(DRACULA_URL));

        frankensteinThread.start();
        draculaThread.start();

        // Wait for both threads to complete
        frankensteinThread.join();
        draculaThread.join();

        // Find and print the top 5 most common words
        List<Map.Entry<String, Integer>> top5 = wordCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .toList();

        System.out.println("Top 5 most common words across both texts:");
        for (Map.Entry<String, Integer> entry : top5) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }

    private static void processServer(String serverUrl) {
        try {
            URL url = new URL(serverUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Clean and split the line into words
                    String[] words = line.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
                    for (String word : words) {
                        if (!word.isEmpty()) {
                            // Thread-safe word count update
                            wordCount.merge(word, 1, Integer::sum);
                        }
                    }
                }
            }
            conn.disconnect();
        } catch (Exception e) {
            System.err.println("Error processing server " + serverUrl + ": " + e.getMessage());
        }
    }
}
