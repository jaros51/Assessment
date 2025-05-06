package sk.wm.server2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.wm.server2.Server2Repo;

@Service
public class Server2Service {

    @Autowired
    private Server2Repo server1Repo;

    public String getServer1Text() {

        return server1Repo.getDraculaText();
    }
}
