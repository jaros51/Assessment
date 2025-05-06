package sk.wm.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@Configuration
@EnableScheduling
public class ScheduledJob {

    @Autowired
    ClientService clientService;

    @Scheduled(fixedRate = 5000)

    public void processServers(){

        System.out.println("KOKOOO");

         clientService.callServer(8081);
         clientService.callServer(8082);

    }

}
