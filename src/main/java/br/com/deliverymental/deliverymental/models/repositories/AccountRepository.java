package br.com.deliverymental.deliverymental.models.repositories;

import br.com.deliverymental.deliverymental.models.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query(value = "SELECT a FROM Account a WHERE a.email = :email")
    Optional<Account> findAccountByEmail(String email);
}
