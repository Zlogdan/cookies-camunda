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
@RequestMapping("/devices/v1/mixer/")
@RequiredArgsConstructor
public class MixerController {
    private final FakeStatus fakeStatus;
    private final FakeDevicesDuration duration;

    @GetMapping("/status")
    public Map<String, Boolean> getStatus() throws InterruptedException {
        Map<String, Boolean> status = new HashMap<>();
        status.put("status", fakeStatus.getMixerStatus());
        Thread.sleep(duration.getMixerStatus()); //добавляем искуственную задержку ответа для
        return status;
    }

    @GetMapping("/start")
    public Map<String, Boolean> startMixer(@RequestParam(value = "time") Long time) throws InterruptedException {
        Map<String, Boolean> work = new HashMap<>();
        work.put("status", fakeStatus.getMixerWork());
        Thread.sleep(duration.getMixerWork()); //добавляем искуственную задержку ответа для
        return work;
    }
}
