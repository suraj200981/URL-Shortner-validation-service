package com.example.url.shortner.microservices.validationservice.validation;

import com.example.url.shortner.microservices.validationservice.model.Url;

public class UrlValidation {

    private String url;
    public UrlValidation(String url){
        this.url=url;
    }

    public Boolean validateURL(String url){
        return true;
    }

}
