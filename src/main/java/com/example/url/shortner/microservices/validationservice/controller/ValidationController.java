package com.example.url.shortner.microservices.validationservice.controller;

import com.example.url.shortner.microservices.validationservice.client.ShortenerClient;
import com.example.url.shortner.microservices.validationservice.model.Url;
import com.example.url.shortner.microservices.validationservice.model.UrlDTO;
import com.example.url.shortner.microservices.validationservice.service.PrefixCheck;
import com.example.url.shortner.microservices.validationservice.validation.UrlValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
public class ValidationController {

    @Autowired
    private UrlValidation urlValidation;

    @Autowired
    private PrefixCheck prefixCheck;

    @Autowired
    private ShortenerClient shortenerClient;

    @PostMapping("/validation")
    public ResponseEntity<String> validationCheck(@RequestBody Url url, @RequestHeader HttpHeaders httpHeaders) {
        try {
            log.info("Url posted: {}", url.getUrl());

            // Validate URL
            if (urlValidation.validateURL(url.getUrl())) {
                UrlDTO dto = new UrlDTO();
                dto.setOriginalUrl(url.getUrl());
                log.info("Proceeding with prefix inspection");
                dto.setPrefix(prefixCheck.prefixStringInspector(url.getUrl()).equals("false") ? "https://" : prefixCheck.prefixStringInspector(url.getUrl()));

                // Set createdAt to the current date and time
                dto.setCreatedAt(LocalDateTime.now());
                log.info("Url validation check passed");
                log.info("Url sent to shortener microservice");

                // Convert HttpHeaders to a Map<String, String> for WebClient
                Map<String, String> headersMap = new HashMap<>();
                httpHeaders.forEach((key, values) -> headersMap.put(key, String.join(",", values)));

                // Call the WebClient method from ShortenerClient and block to get the response
                String response = shortenerClient.shortenUrl(dto, headersMap).block();

                // Return the response from the URL shortening service
                return ResponseEntity.ok(response);
            } else {
                log.error("URL validation check failed. Please check length/placement of '.' in the URL");
                return ResponseEntity.badRequest().body("URL validation check failed.");
            }
        } catch (Exception e) {
            log.error("An error occurred during URL validation or shortening process: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("An internal error occurred. Please try again later.");
        }
    }
}
