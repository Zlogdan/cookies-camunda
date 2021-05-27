package ru.nata.diploma.cookies.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
public class Rest {
    private final RestTemplate restTemplate;

    public Rest(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofMinutes(30))
                .setReadTimeout(Duration.ofMinutes(50))
                .build();

    }

    public RestTemplate getTemplate() {
        return restTemplate;
    }
}
