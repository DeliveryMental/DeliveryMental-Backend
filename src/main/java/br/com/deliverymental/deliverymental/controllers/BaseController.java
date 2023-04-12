package br.com.deliverymental.deliverymental.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class BaseController {//TODO: REFATORAR

    @GetMapping(value = "/")
    public String version() {
        return "Back-end Delivery Mental 1.0.0";
    }

    @GetMapping(value = "/credits")
    public String credits() {
        return "https://github.com/DeliveryMental";
    }
}
