package micro.service.review;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Map;
import java.util.Properties;

public class CustomPropertiesListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private final ConfigClient configClient = new ConfigClient();

    private Map<String, String> sources;

    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        Properties props = new Properties();
        sources = configClient.getSources("user", "sccs");
        props.put("api.key", getSecret("api.key"));
        environment.getPropertySources().addFirst(new PropertiesPropertySource("myProps", props));
    }

    private String getSecret(String key) {
        return new EncryptionUtils().decrypt(sources.get(key));
    }
}
