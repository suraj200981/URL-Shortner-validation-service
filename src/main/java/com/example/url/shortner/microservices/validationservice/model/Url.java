package com.example.url.shortner.microservices.validationservice.model;

import lombok.Data;

@Data //with lombok.Data we do not need to define getters and setters, this happens automatically.
public class Url {

    private String url;
}
