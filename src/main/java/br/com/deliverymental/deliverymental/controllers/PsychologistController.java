package br.com.deliverymental.deliverymental.controllers;

import br.com.deliverymental.deliverymental.models.repositories.PsychologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/psychologist")
public class PsychologistController {

    @Autowired
    private PsychologistRepository psychologistRepository;

    @GetMapping(value = "/")
    public String version() {
        return "Back-end Delivery Mental Psychologist Route 1.0.0";
    }
}
