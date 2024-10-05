package com.example.url.shortner.microservices.validationservice.client;

import com.example.url.shortner.microservices.validationservice.model.UrlDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Component
public class ShortenerClient {

    private final WebClient webClient;

    // Constructor with WebClient configuration
    public ShortenerClient(WebClient.Builder webClientBuilder, @Value("${shortener.service.url}") String shortenerServiceUrl) {
        this.webClient = webClientBuilder
                .baseUrl(shortenerServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    // Method to send POST request to shortener service
    public Mono<String> shortenUrl(UrlDTO urlDTO, Map<String, String> headers) {
        return webClient.post()
                .uri("http://localhost:8100/shorten")  // Endpoint to send the POST request to
                .headers(httpHeaders -> httpHeaders.setAll(headers))  // Add custom headers if any
                .bodyValue(urlDTO)  // Set the body to the UrlDTO object
                .retrieve()  // Retrieve the response
                .bodyToMono(String.class);  // Convert response body to Mono<String>
    }
}
