package ru.nata.diploma.cookies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.config.Rest;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MixerService {
    private final Rest rest;

    @Value("${devices.url}")
    private String devicesUrl;

    public Map<String, String> startMixer(Long time) {
        Map<String, String> work = new HashMap<>();
        //так как у нас нет настоящего оборудования то делаем просто очень долгое ожидание ответа
        //в случае с реальным оборудованием возможно подход был бы иной
        //можно реализовать через бесконечный цикл опрашивая устройство назавершение цикла
        //или истечение времени ожидания
        Map<String, Long> uriParams = new HashMap<>();
        uriParams.put("time", time);
        Map<String, Boolean> request = rest.getTemplate()
                .getForObject(devicesUrl + "mixer/start?time=30", Map.class, uriParams);

        if (request == null || !request.get("status")) {
            work.put("status", "ERROR");
            return work;
        }
        work.put("status", "OK");
        return work;
    }

}
