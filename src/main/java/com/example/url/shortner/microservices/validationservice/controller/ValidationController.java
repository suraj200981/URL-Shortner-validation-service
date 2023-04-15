package com.example.url.shortner.microservices.validationservice.controller;

import com.example.url.shortner.microservices.validationservice.model.Url;
import com.example.url.shortner.microservices.validationservice.model.UrlDTO;
import com.example.url.shortner.microservices.validationservice.request.ProxyRequest;
import com.example.url.shortner.microservices.validationservice.validation.UrlValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ValidationController {

    @Autowired
    private UrlValidation urlValidation;

    @Autowired
    private ProxyRequest proxyRequest;

    public ValidationController(ProxyRequest proxyRequest) {
        this.proxyRequest = proxyRequest;
    }


    @PostMapping("/validation")
    public void validationCheck(@RequestBody Url url ){
        System.out.println("Url posted: "+url.getUrl());

        if(urlValidation.validateURL(url.getUrl())){
            log.info("Url validation check passed");
            UrlDTO dto = new UrlDTO();
            dto.setOriginalUrl(url.toString());
            proxyRequest.shortenUrlRequest(dto);
        }else {
            log.error("URL validation check failed. Please check length/placement of '.' in the url");
        }
    }
}
