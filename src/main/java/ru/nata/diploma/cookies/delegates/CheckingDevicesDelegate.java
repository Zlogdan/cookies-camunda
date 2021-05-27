package ru.nata.diploma.cookies.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.models.Log;
import ru.nata.diploma.cookies.repositories.LogRepository;
import ru.nata.diploma.cookies.services.CheckingDevicesService;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CheckingDevicesDelegate implements JavaDelegate {
    private final CheckingDevicesService checkingDevicesService;
    private final LogRepository logRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        LocalDateTime start = LocalDateTime.now();
        Log current = new Log();
        current.setOperation("checkingDevicesService");
        Integer error = (Integer) delegateExecution.getVariable("ERROR");

        Map<String, String> deviceStatus = checkingDevicesService.getDeviceStatus();
        String status = deviceStatus.get("status");
        if (!status.equals("OK")) {
            current.setStatus(status);
            current.setComment(deviceStatus.get("devices"));
            delegateExecution.setVariable("ERROR", error + 1);
        } else {
            current.setStatus(status);
        }

        LocalDateTime finish = LocalDateTime.now();
        current.setProcessId(delegateExecution.getProcessInstanceId());
        current.setStartTime(start);
        current.setFinishTime(finish);

        logRepository.save(current);
    }
}
