package demo.messaging;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.util.FileSystemUtils;

import javax.jms.ConnectionFactory;
import java.io.File;

@SpringBootApplication
@EnableJms
public class MessageReaderApplication {

    private static final int PORT = 32786;

    @Bean
    static ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.99.100:" + PORT);
        cf.setUserName("admin");
        cf.setPassword("admin");
        return cf;
    }

    // Strictly speaking this bean is not necessary as boot creates a default
    @Bean
    JmsListenerContainerFactory<?> myJmsContainerFactory() {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

    public static void main(String[] args) {

        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(MessageReaderApplication.class, args);

        while(true) {
            // run JMS Receiver until forcibly closed
        }
	}
}
