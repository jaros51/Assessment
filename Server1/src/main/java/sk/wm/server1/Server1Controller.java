package sk.wm.server1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.http.HttpResponse;
import java.util.Optional;

@Controller
public class Server1Controller {

    @Autowired
    private Server1Service server1Service;

    @GetMapping("/")
    public ResponseEntity<String> getServer1() {
        String output = server1Service.getServer1Text();
        return ResponseEntity
                .ok()
                .body(output);
    }
}
