package com.example.url.shortner.microservices.validationservice.service;

public class DotFinder {

    public DotFinder(){

    }
    public Boolean findDot(String url) {
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '.') {
                return true;
            }
        }
        return false;
    }
}
