package br.com.deliverymental.deliverymental.controllers;

import br.com.deliverymental.deliverymental.DeliveryMentalApplication;
import br.com.deliverymental.deliverymental.cryptography.SHA256;
import br.com.deliverymental.deliverymental.models.entities.Account;
import br.com.deliverymental.deliverymental.models.repositories.AccountRepository;
import br.com.deliverymental.deliverymental.services.EmailType;
import br.com.deliverymental.deliverymental.services.emails.AccountCreatedEmail;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(value = "/")
    public String version() {
        return "Back-end Delivery Mental Account Route 1.0.0";
    }

    @GetMapping(value = "/check")
    public ResponseEntity<Object> hasAccount(@PathVariable(value = "email") String email) {
        Optional<Account> account = accountRepository.findAccountByEmail(email);
        if (account.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /* JSON
    {
	"firstName": "Felipe",
	"lastName": "Simão",
	"email": "feliperos.simao@gmail.com",
	"password": "1234",
	"roleID": "2"
    }
     */

    @PostMapping(value = "/create") //TODO: APENAS CÓDIGO DE TESTE!
    public ResponseEntity<Object> createAccount(@Valid @RequestBody Account account) {
        HttpStatusCode statusCode = hasAccount(account.getEmail()).getStatusCode();

        if (statusCode.is4xxClientError()) {
            account.setCreatedAt(Date.from(Instant.now()));
            account.setUpdatedAt(Date.from(Instant.now()));
            account.setPassword(SHA256.encrypt(account.getPassword()));
            Account createdAccount = accountRepository.save(account);

            if (createdAccount == null) {
                return ResponseEntity.notFound().build();
            } else {
                URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .buildAndExpand(createdAccount.getId())
                        .toUri();
                /*
                AccountCreatedEmail newAccount = new AccountCreatedEmail(createdAccount.getEmail(), EmailType.CreateAccount, createdAccount);
                newAccount.makeEmail();
                 */
                DeliveryMentalApplication.getQueueManager().addEmailToQueue(new AccountCreatedEmail(createdAccount.getEmail(), EmailType.CreateAccount, createdAccount));
                /*
                List<Emails>
                list.add()

                Scheduler.consume(list) -> 1m
                 */
                return ResponseEntity.created(uri).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Email já cadastrado!");
        }
    }

    /*
    @PostMapping(value = "/create")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {

        HttpStatusCode status = hasAccount(account.getEmail()).getStatusCode();

        if (status.is4xxClientError()) {
            account.setCreatedAt(Date.from(Instant.now()));
            account.setUpdatedAt(Date.from(Instant.now()));
            account.setPassword(SHA256.encrypt(account.getPassword()));
            Account createdAccount = accountRepository.save(account);
            if (createdAccount == null) {
                return ResponseEntity.notFound().build();
            } else {
            /*
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(createdAccount.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdAccount);
            *
                return ResponseEntity.accepted().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
     */



    /*
    @PostMapping(value = "/create")
    public Account createAccount(@Valid @RequestBody Account account) {
        account.setCreatedAt(Date.from(Instant.now()));
        account.setUpdatedAt(Date.from(Instant.now()));
        account.setPassword(SHA256.encrypt(account.getPassword()));
        return accountRepository.save(account);
    }
     */

    /*
    @GetMapping(value = "/email/validate/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> checkEmail(@PathVariable(value = "email") String email) {
        Optional<Account> account = accountRepository.findAccountByEmail(email);
        if (account.isPresent()) {
            return new ResponseEntity<>(Collections.singletonMap("validAccount", "true"), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
     */

    /*
    @GetMapping(value = "/password/validate/{email}/{password}")
    public ResponseEntity<String> validatePassword(@PathVariable(value = "email") String email, @PathVariable(value = "password") String password) {
        Optional<Account> account = accountRepository.findAccountByEmail(email);
        if (account.isPresent()) {
            String encryptedPassword = SHA256.encrypt(password);
            System.out.println(encryptedPassword);
            if (encryptedPassword.equals(account.get().getPassword())) {
                return new ResponseEntity<>("match", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("unMatch", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("false", HttpStatus.NOT_FOUND);
        }
    }
     */
}
