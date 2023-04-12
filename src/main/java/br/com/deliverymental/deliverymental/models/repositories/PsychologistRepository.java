package br.com.deliverymental.deliverymental.models.repositories;

import br.com.deliverymental.deliverymental.models.entities.Psychologist;
import org.springframework.data.repository.CrudRepository;

public interface PsychologistRepository extends CrudRepository<Psychologist, Long> {
}
