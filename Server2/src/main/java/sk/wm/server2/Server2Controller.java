package sk.wm.server2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
public class Server2Controller {

    @Value("${file.path}")
    private String filePath;

    @GetMapping("/")
    public ResponseEntity<StreamingResponseBody> streamFile() {
        StreamingResponseBody stream = out -> {
            try (InputStream inputStream = new FileInputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        };
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(stream);
    }
}
