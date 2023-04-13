package br.com.deliverymental.deliverymental.services;

import br.com.deliverymental.deliverymental.DeliveryMentalApplication;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Email {
    private final String to;
    private final EmailType type;

    public Email(String to, EmailType type) {
        this.to = to;
        this.type = type;
    }

    public String getEmailContent() throws IOException {
        Path filePath = Path.of(type.getFilePath());
        return Files.readString(filePath);
    }

    protected void sendMail(String emailContent) {
        Session session = DeliveryMentalApplication.getSession();
        session.setDebug(false);

        String from = "DeliveryMental";

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(type.getTitle());
            message.setContent(emailContent, "text/html");
            Transport.send(message);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public abstract void makeEmail();
}
