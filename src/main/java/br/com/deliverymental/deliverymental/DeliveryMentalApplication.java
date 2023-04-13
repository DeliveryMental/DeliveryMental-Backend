package br.com.deliverymental.deliverymental;

import br.com.deliverymental.deliverymental.services.queue.EmailQueueManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.ArrayList;
import java.util.Properties;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DeliveryMentalApplication {
    private static Session session;
    private static EmailQueueManager queueManager;

    public static void main(String[] args) {
        session = Session.getInstance(getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        });

        queueManager = new EmailQueueManager(new ArrayList<>());

        SpringApplication.run(DeliveryMentalApplication.class, args);
    }

    private static Properties getProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    public static Session getSession() {
        return session;
    }

    public static EmailQueueManager getQueueManager() {
        return queueManager;
    }
}
