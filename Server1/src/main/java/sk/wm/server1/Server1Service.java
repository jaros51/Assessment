package sk.wm.server1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Server1Service {

    @Autowired
    private Server1Repo server1Repo;

    public String getServer1Text() {

        return server1Repo.getDraculaText();
    }
}
