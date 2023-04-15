package com.example.url.shortner.microservices.validationservice;

import com.example.url.shortner.microservices.validationservice.request.ProxyRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
public class ValidationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidationServiceApplication.class, args);
	}

}
