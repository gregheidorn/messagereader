package demo.messaging;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by gregheidorn on 3/25/16.
 */
@ConfigurationProperties()
public class ActiveMQConnectionFactoryProperties {
    private String brokerUrl = "tcp://192.168.99.100:61616";
    private boolean inMemory = true;
    private boolean pooled = false;
    public String getBrokerUrl() {
        if (this.inMemory) {
            return "vm://localhost";
        } else {
            return this.brokerUrl;
        }
    }
}
