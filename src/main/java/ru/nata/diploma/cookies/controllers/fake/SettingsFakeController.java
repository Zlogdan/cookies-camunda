package ru.nata.diploma.cookies.controllers.fake;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nata.diploma.cookies.config.FakeDevicesDuration;
import ru.nata.diploma.cookies.config.FakeStatus;

@RestController
@RequestMapping("/devices/v1/")
@RequiredArgsConstructor
public class SettingsFakeController {
    private final FakeDevicesDuration duration;
    private final FakeStatus fakeStatus;

    @PostMapping("/set-duration")
    public FakeDevicesDuration setDuration(@RequestBody FakeDevicesDuration newDuration) {
        duration
                .setMixerStatus(newDuration.getMixerStatus())
                .setFurnaceStatus(newDuration.getFurnaceStatus())
                .setHolderStatus(newDuration.getHolderStatus())
                .setAnalyzerStatus(newDuration.getAnalyzerStatus())
                .setHolderWork(newDuration.getHolderWork())
                .setFurnaceWork(newDuration.getFurnaceWork())
                .setMixerWork(newDuration.getMixerWork())
                .setAnalyzerWork(newDuration.getAnalyzerWork());
        return duration;
    }

    @PostMapping("/set-status")
    public FakeStatus setStatus(@RequestBody FakeStatus newFakeStatus) {
        fakeStatus
                .setAnalyzerStatus(newFakeStatus.getAnalyzerStatus())
                .setFurnaceStatus(newFakeStatus.getFurnaceStatus())
                .setHolderStatus(newFakeStatus.getHolderStatus())
                .setMixerStatus(newFakeStatus.getMixerStatus())
                .setAnalyzerWork(newFakeStatus.getAnalyzerWork())
                .setFurnaceWork(newFakeStatus.getFurnaceWork())
                .setHolderWork(newFakeStatus.getHolderWork())
                .setMixerWork(newFakeStatus.getMixerWork());
        return fakeStatus;
    }

    @GetMapping("/get-duration")
    public FakeDevicesDuration getDuration() {
        return duration;
    }

    @GetMapping("/get-status")
    public FakeStatus getStatus() {
        return fakeStatus;
    }
}
