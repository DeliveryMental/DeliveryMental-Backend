package br.com.deliverymental.deliverymental.services.queue;

import br.com.deliverymental.deliverymental.services.Email;

import java.util.List;

public class EmailQueueManager {
    private final List<Email> emailsToProcess;

    public EmailQueueManager(List<Email> emailsToProcess) {
        this.emailsToProcess = emailsToProcess;
    }

    public void addEmailToQueue(Email email) {
        this.emailsToProcess.add(email);
    }

    public List<Email> getEmailsInQueue() {
        return this.emailsToProcess;
    }

    public int getLastRegister() {
        return this.emailsToProcess.size();
    }
}
