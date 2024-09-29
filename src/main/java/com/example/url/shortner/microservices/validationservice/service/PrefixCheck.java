package com.example.url.shortner.microservices.validationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PrefixCheck {

        public String prefixStringInspector(String prefix) throws Exception {

            try {
                if (prefix.startsWith("http://")) {
                    prefix = prefix.substring(0, 7);
                    return prefix;
                } else if (prefix.startsWith("https://")) {
                    prefix = prefix.substring(0, 8);
                    return prefix;
                } else {
                    log.info("No vaild prefix detected ");
                    return "false";
                }
            }
            catch(Exception e) {
            log.error("An error occured with prefix validation");
            throw (new Exception(e));
            }
        }

}
