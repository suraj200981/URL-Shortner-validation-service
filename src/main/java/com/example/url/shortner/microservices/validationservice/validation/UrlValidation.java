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

    public Boolean validateURL(String url) {
        if (lengthCheck.checkLength(url)) {

            log.info("Length check passed");

            if (prefixCheck.checkPrefix(url)) {
                return true;
            }else{
                //http prefix not present
                return true;

            }
        } else {
            log.error("Validation checks of url have failed");
            return false;
        }
    }

}
