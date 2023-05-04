package com.example.url.shortner.microservices.validationservice.request;

import com.example.url.shortner.microservices.validationservice.model.UrlDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "url-shortener-service", url = "localhost:8100") //local development
//@FeignClient(name = "url-shortener-service", url = "urlshortener-service:8100")
public interface ProxyRequest {
    @PostMapping("/shorten")
    ResponseEntity<String> shortenUrlRequest(@RequestBody UrlDTO urlDTO);
}
