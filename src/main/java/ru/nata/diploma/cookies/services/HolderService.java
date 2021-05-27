package ru.nata.diploma.cookies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.config.Rest;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HolderService {
    private final Rest rest;

    @Value("${devices.url}")
    private String devicesUrl;

    public Map<String, String> getReadyHolder() throws InterruptedException {
        Map<String, Long> uriParams = new HashMap<>();
        Map<String, String> work = new HashMap<>();
        Map<String, Boolean> request = new HashMap<>();

        //так ка мы не можем наверняка знать когда хранилише будет готово,
        //мы выставляем количество попыток опроса и время между попытками
        //в ином случае считаем что эмульсатор не отравботал
        //и возвращаем ошибку
        int tryCount = 3;
        int tryDelay = 1000;
        Boolean ready = false;
        while (tryCount >= 0 && !ready) {
            request = rest.getTemplate()
                    .getForObject(devicesUrl + "holder/ready", Map.class, uriParams);
            if (request != null) {
                ready = request.get("status");
            }
            Thread.sleep(tryDelay); //добавляем задержку медлу проверками
            tryCount = tryCount - 1;
        }

        if (!ready) {
            work.put("status", "ERROR");
            return work;
        }

        work.put("status", "OK");
        return work;
    }

}
