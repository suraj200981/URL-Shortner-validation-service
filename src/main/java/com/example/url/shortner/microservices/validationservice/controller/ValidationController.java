package com.example.url.shortner.microservices.validationservice.controller;

import com.example.url.shortner.microservices.validationservice.model.Url;
import com.example.url.shortner.microservices.validationservice.validation.UrlValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
public class ValidationController {

    @Autowired
    private UrlValidation urlValidation;

    @PostMapping("/validation")
    public void validationCheck(@RequestBody Url url ){
        System.out.println("Url posted: "+url.getUrl());

        if(urlValidation.validateURL(url.getUrl())){
            //proceed with forwarding request to shorten-service

        }
    }
}
