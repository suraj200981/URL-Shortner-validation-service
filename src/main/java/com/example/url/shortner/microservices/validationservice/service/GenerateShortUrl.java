package com.example.url.shortner.microservices.validationservice.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class GenerateShortUrl {

    public String mainURL = "localhost:8000";

    public String generateNewUrl(String url, String prefix){

        //eventually save the orginal url somehow
        //String orginalURL = prefix + url;

        return mainURL+"/"+ RandomStringUtils.random(5,true,true);

    }
}
