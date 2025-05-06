package sk.wm.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ClientService {


//    // call Server1Application and get text from "/" with socket localhost:8081
//    void callServer(int port){
//        try {
//            // Create a URL object with the server address
//            URL url = new URL("http://localhost:" + port + "/");
//            // Open a connection to the server
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            // Set the request method to GET
//            connection.setRequestMethod("GET");
//            // Get the response code
//            int responseCode = connection.getResponseCode();
//            // If the response code is 200 (HTTP_OK), read the response
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//                System.out.println("Response from server: " + response.toString().substring(0, 100));
//            } else {
//                System.out.println("Failed to connect to server. Response code: " + responseCode);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private final RestTemplate restTemplate = new RestTemplate();

    void callServer(int port) {
        try {
            String url = "http://localhost:" + port + "/";
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("Response from server: " + response.substring(0, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
