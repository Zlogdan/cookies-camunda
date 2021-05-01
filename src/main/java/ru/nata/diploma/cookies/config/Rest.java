package ru.nata.diploma.cookies.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Rest {
    private final RestTemplate restTemplate;

    public Rest( RestTemplateBuilder restTemplateBuilder ) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public RestTemplate getTemplate() {
        return restTemplate;
    }
}
