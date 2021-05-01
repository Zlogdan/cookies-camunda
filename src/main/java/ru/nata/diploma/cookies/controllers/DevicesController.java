package ru.nata.diploma.cookies.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nata.diploma.cookies.config.FakeStatus;
import ru.nata.diploma.cookies.models.DevicesStatus;

@RestController
@RequestMapping("/devices/v1/")
@RequiredArgsConstructor
public class DevicesController {
    private final FakeStatus fakeStatus;

    @GetMapping("/get-status")
    private DevicesStatus getStatus() {
        return fakeStatus.getDevicesStatus();
    }

}
