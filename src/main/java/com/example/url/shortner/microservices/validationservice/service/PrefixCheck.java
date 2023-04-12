package com.example.url.shortner.microservices.validationservice.service;

import org.springframework.stereotype.Component;

@Component
public class PrefixCheck {


        public Boolean checkPrefix(String url){
            if(prefixStringInspector(url).equals("false")) {
                return false;
            } else if (prefixStringInspector(url).equals("http://")) {
                System.out.println("Correct prefix found");
                return true;
            } else if (prefixStringInspector(url).equals("https://")) {
                System.out.println("Correct prefix found");
                return true;
            }
        return false;
        }


        public String prefixStringInspector(String prefix) {
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

}
