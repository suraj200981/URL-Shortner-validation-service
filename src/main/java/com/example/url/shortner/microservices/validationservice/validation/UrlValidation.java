package com.example.url.shortner.microservices.validationservice.validation;

import com.example.url.shortner.microservices.validationservice.model.Url;
import com.example.url.shortner.microservices.validationservice.service.GenerateShortUrl;
import com.example.url.shortner.microservices.validationservice.service.LengthCheck;
import com.example.url.shortner.microservices.validationservice.service.PrefixCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UrlValidation {
    @Autowired
    LengthCheck lengthCheck;

    @Autowired
    PrefixCheck prefixCheck;

    @Autowired
    GenerateShortUrl generateShortUrl;

    public Boolean validateURL(String url){
        if(lengthCheck.checkLength(url)){

            System.out.println("proceed with prefix check");

            if (prefixCheck.checkPrefix(url)) {

                System.out.println(generateShortUrl.generateNewUrl(url, ""));

            }else {
                System.out.println(generateShortUrl.generateNewUrl(url, "http://"));
            }
            return true;

        }else {
            throw new RuntimeException("Failed url length check/dot check failed");
        }
    }

}
