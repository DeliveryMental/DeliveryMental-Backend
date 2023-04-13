package br.com.deliverymental.deliverymental.schedulers.tasks;

import br.com.deliverymental.deliverymental.DeliveryMentalApplication;
import br.com.deliverymental.deliverymental.services.Email;

import java.util.List;

public class SendMailTask implements Runnable {
    @Override
    public void run() {
        int maxIndex = DeliveryMentalApplication.getQueueManager().getLastRegister();
        List<Email> emails = DeliveryMentalApplication.getQueueManager().getEmailsInQueue();

        if (maxIndex == 0) System.out.println("Não existem emails para serem enviados!");

        for (int i = 0; i < maxIndex; i++) {
            Email email = emails.get(i);
            email.makeEmail();
            DeliveryMentalApplication.getQueueManager().getEmailsInQueue().remove(i);
            System.out.println("1 Email foi enviado!");
        }
    }
}
