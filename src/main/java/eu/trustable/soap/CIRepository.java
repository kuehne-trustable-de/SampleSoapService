package eu.trustable.soap;

import eu.trustable.soap.sample.ci.Ci;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CIRepository {

    private static final Map<String, Ci> cis = new HashMap<>();

    @PostConstruct
    public void initData() {

        addCi(10, "artifactory");
        addCi(11, "jitsi");
        addCi(12, "jenkins");
        addCi(13, "ca3s");
        addCi(14, "mail");

    }

    private Ci addCi(int id, final String name) {
        Ci artifactory = new Ci();
        artifactory.setId(id);
        artifactory.setName(name);
        artifactory.setEmail("admin@trustable.eu");
        artifactory.setActive(true);
        artifactory.setUrl(name + ".trustable.eu");
        cis.put(artifactory.getUrl(), artifactory);

        return artifactory;
    }

    public Ci findCiByUrl(String name) {
        Assert.notNull(name, "The ci's url must not be null");
        return cis.get(name);
    }
}
