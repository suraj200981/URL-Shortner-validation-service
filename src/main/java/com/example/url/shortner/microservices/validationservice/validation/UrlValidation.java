package com.example.url.shortner.microservices.validationservice.validation;

import com.example.url.shortner.microservices.validationservice.model.UrlDTO;
import com.example.url.shortner.microservices.validationservice.request.ProxyRequest;
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

    @Autowired
    ProxyRequest proxyRequest;

    public Boolean validateURL(String url){
        if(lengthCheck.checkLength(url)){

            log.info("Length check passed");

            if (prefixCheck.checkPrefix(url)) {

                log.info("Prefix check passed");
                UrlDTO dto = new UrlDTO();
                dto.setOriginalUrl(url);
                proxyRequest.shortenUrlRequest(dto);
                return true;

            }else {
                log.info("Prefix check passed but prefix needs to be added");
                UrlDTO dto = new UrlDTO();
                dto.setOriginalUrl(url);
                dto.setPrefix("https://");
                proxyRequest.shortenUrlRequest(dto);
                return true;
            }
        }else {
            log.error("Validation checks of url have failed");
            return false;
        }
    }

}
