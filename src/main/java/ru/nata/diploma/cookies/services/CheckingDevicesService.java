package ru.nata.diploma.cookies.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.config.Rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CheckingDevicesService {
    private final Rest rest;

    @Value("${devices.url}")
    private String devicesUrl;

    /**
     * Получаем статус всех устройств по ресту
     * и прореряем на наличие ошибок
     *
     * @return есть ошибки: статус error, список устройств с ошибкой
     * нет ошибок : статус OK
     */
    public Map<String, String> getDeviceStatus() {
        Map<String, String> result = new HashMap<>();
        Map<String, Map<String, Boolean>> statuses = new HashMap<>();

        statuses.put("analyzer",rest.getTemplate().getForObject(devicesUrl + "analyzer/status", Map.class));
        statuses.put("mixer",rest.getTemplate().getForObject(devicesUrl + "mixer/status", Map.class));
        statuses.put("furnace",rest.getTemplate().getForObject(devicesUrl + "furnace/status", Map.class));
        statuses.put("holder",rest.getTemplate().getForObject(devicesUrl + "holder/status", Map.class));

        List<String> errorList = new ArrayList<>();
        statuses.forEach((device, status) -> {
            if (!status.get("status")) {
                errorList.add(device);
            }
        });

        if (!errorList.isEmpty()) {
            result.put("status", "ERROR");
            String devices = "error devices: " + StringUtils.join(errorList, ',');
            result.put("devices", devices);
            return result;
        }
        result.put("status", "OK");
        return result;

    }
}
