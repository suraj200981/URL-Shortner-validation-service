package com.example.url.shortner.microservices.validationservice.controller;

import com.example.url.shortner.microservices.validationservice.model.Url;
import com.example.url.shortner.microservices.validationservice.model.UrlDTO;
import com.example.url.shortner.microservices.validationservice.request.ProxyRequest;
import com.example.url.shortner.microservices.validationservice.service.PrefixCheck;
import com.example.url.shortner.microservices.validationservice.validation.UrlValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@CrossOrigin
@RestController
public class ValidationController {

    @Autowired
    private UrlValidation urlValidation;

    @Autowired
    ProxyRequest proxyRequest;

    @Autowired
    PrefixCheck prefixCheck;

    @PostMapping("/validation")
    public ResponseEntity<String> validationCheck(@RequestBody Url url ) throws Exception {
        System.out.println("Url posted: "+url.getUrl());

        if(urlValidation.validateURL(url.getUrl())){
            UrlDTO dto = new UrlDTO();
            dto.setOriginalUrl(url.getUrl());
            log.info("Proceeding with prefix inspection");
            if (prefixCheck.prefixStringInspector(url.getUrl()).equals("false")) {
                dto.setPrefix("https://");

            }else{
                dto.setPrefix(prefixCheck.prefixStringInspector(url.getUrl()));
            }
            Date date = new Date();
            dto.setCreatedAt(date);
            log.info("Url validation check passed");
            log.info("Url sent to shortener microservice");
          return  proxyRequest.shortenUrlRequest(dto);


        }else {
            log.error("URL validation check failed. Please check length/placement of '.' in the url");
        }
      return null;
    }
}
