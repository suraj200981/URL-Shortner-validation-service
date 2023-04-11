package com.example.url.shortner.microservices.validationservice.validation;

import com.example.url.shortner.microservices.validationservice.model.Url;
import com.example.url.shortner.microservices.validationservice.service.LengthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UrlValidation {
    @Autowired
    LengthCheck lengthCheck;

    public Boolean validateURL(String url){
        if(lengthCheck.checkLength(url)){

            System.out.println("proceed with prefix check");

        }else {
            throw new RuntimeException("Failed url length check/dot check failed");
        }

        return true;
    }

}
