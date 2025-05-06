package sk.wm.server1;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Repository
public class Server1Repo {

    // read text from file dracula.txt
    public String getDraculaText() {
        try (InputStream inputStream = getClass().getResourceAsStream("/static/dracula.txt")) {
            if (inputStream == null) {
                throw new IOException("File not found");
            }
            byte[] bytes = inputStream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file";
        }
    }
}
