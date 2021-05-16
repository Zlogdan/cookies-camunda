package ru.nata.diploma.cookies.controllers.fake.devices;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nata.diploma.cookies.config.FakeDevicesDuration;
import ru.nata.diploma.cookies.config.FakeStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/devices/v1/analyzer/")
@RequiredArgsConstructor
public class AnalyzerController {
    private final FakeStatus fakeStatus;
    private final FakeDevicesDuration duration;

    @GetMapping("/status")
    public Map<String, Boolean> getStatus() throws InterruptedException {
        Map<String, Boolean> status = new HashMap<>();
        status.put("status", fakeStatus.getAnalyzerStatus());
        Thread.sleep(duration.getAnalyzerStatus()); //добавляем искуственную задержку ответа для
        return status;
    }

    @GetMapping("/start")
    public Map<String, Boolean> startAnalyzer(@RequestParam(value = "deviation") Long deviation) throws InterruptedException {
        Map<String, Boolean> work = new HashMap<>();
        work.put("status", fakeStatus.getAnalyzerWork());
        Thread.sleep(duration.getAnalyzerWork()); //добавляем искуственную задержку ответа для
        return work;
    }

}
