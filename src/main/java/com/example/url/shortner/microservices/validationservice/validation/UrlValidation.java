package com.example.url.shortner.microservices.validationservice.validation;

import com.example.url.shortner.microservices.validationservice.service.LengthCheck;
import com.example.url.shortner.microservices.validationservice.service.PrefixCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class UrlValidation {
    @Autowired
    LengthCheck lengthCheck;

    @Autowired
    PrefixCheck prefixCheck;


    public Boolean validateURL(String url){
        if(lengthCheck.checkLength(url)){

            log.info("Proceed with prefix check");

            if (prefixCheck.checkPrefix(url)) {

                log.info("Validation checks of url have passed");

            }else {
//                System.out.println(generateShortUrl.generateNewUrl(url, "http://"));
            }
            return true;

        }else {
            log.error("Validation checks of url have failed");

            throw new RuntimeException("Failed url length check/dot check failed");
        }
    }

}
