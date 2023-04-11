package com.example.url.shortner.microservices.validationservice.service;

public class prefixCheck {


        public Boolean checkPrefix(String url){


        }


        public String prefixStringInspector(String prefix){
            if (prefix.startsWith("http://")) {
                 prefix = prefix.substring(0, 7);
                return prefix;
            } else if (prefix.startsWith("https://")) {
                 prefix = prefix.substring(0, 8);
                return prefix;
            } else {
                return "false";
        }

}
