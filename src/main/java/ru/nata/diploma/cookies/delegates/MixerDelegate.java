package ru.nata.diploma.cookies.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.models.HardwareParameters;
import ru.nata.diploma.cookies.models.Log;
import ru.nata.diploma.cookies.repositories.LogRepository;
import ru.nata.diploma.cookies.services.MixerService;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MixerDelegate implements JavaDelegate {
    private final MixerService mixerServices;
    private final LogRepository logRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        LocalDateTime start = LocalDateTime.now();
        Log current = new Log();
        current.setOperation("mixer");
        Integer error = (Integer) delegateExecution.getVariable("ERROR");

        HardwareParameters params = (HardwareParameters) delegateExecution.getVariable("params");
        Map<String, String> mixerResult = mixerServices.startMixer(params.getMixerTime());

        String status = mixerResult.get("status");
        if (!status.equals("OK")) {
            current.setStatus(status);
            delegateExecution.setVariable("ERROR", error + 1);
        } else {
            current.setStatus(status);
        }

        LocalDateTime finish = LocalDateTime.now();
        current.setId(delegateExecution.getProcessInstanceId());
        current.setStartTime(start);
        current.setFinishTime(finish);

        logRepository.save(current);
    }
}
