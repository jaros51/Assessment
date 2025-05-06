package sk.wm.server2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Server2Controller {

    @Autowired
    private Server2Service server1Service;

    @GetMapping("/")
    public ResponseEntity<String> getServer1() {
        String output = server1Service.getServer1Text();
        return ResponseEntity
                .ok()
                .body(output);
    }
}
