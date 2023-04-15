package com.example.url.shortner.microservices.validationservice.request;

import com.example.url.shortner.microservices.validationservice.model.UrlDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "url-shortener-service", url = "localhost:8100")
public interface ProxyRequest {
    @PostMapping("/shorten")
    void shortenUrlRequest(@RequestBody UrlDTO urlDTO);
}
