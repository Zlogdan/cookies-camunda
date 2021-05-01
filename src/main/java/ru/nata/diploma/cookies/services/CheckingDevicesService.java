package ru.nata.diploma.cookies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.config.Rest;

@Service
@RequiredArgsConstructor
public class CheckingDevicesService {
    private final Rest rest;

    public String getDeviceStatus() {
        return rest.getTemplate().getForObject( "http://localhost:8080/devices/v1/get-status", String.class );

    }
//    public CheckingDevicesService( RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }

}
