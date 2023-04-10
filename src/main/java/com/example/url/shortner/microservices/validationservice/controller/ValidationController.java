package com.example.url.shortner.microservices.validationservice.controller;

import com.example.url.shortner.microservices.validationservice.model.Url;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @PostMapping("/validation")
    public void validationCheck(@RequestBody Url url ){
        System.out.println("Url posted: "+url.getUrl());
    }
}
