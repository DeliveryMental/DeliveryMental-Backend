package br.com.deliverymental.deliverymental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DeliveryMentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryMentalApplication.class, args);
    }
}
