package br.com.deliverymental.deliverymental.services.emails;

import br.com.deliverymental.deliverymental.models.entities.Account;
import br.com.deliverymental.deliverymental.services.Email;
import br.com.deliverymental.deliverymental.services.EmailType;

public class AccountCreatedEmail extends Email {
    private Account account;

    public AccountCreatedEmail(String to, EmailType type, Account account) {
        super(to, type);
        this.account = account;
    }

    @Override
    public void makeEmail() {
        try {
            String emailContent = getEmailContent();
            emailContent = emailContent.replaceAll("@\\{NOME}", account.getFirstName());
            sendMail(emailContent);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
