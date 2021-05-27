package ru.nata.diploma.cookies.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.models.Log;
import ru.nata.diploma.cookies.repositories.LogRepository;
import ru.nata.diploma.cookies.services.HolderService;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HolderDelegate implements JavaDelegate {
    private final HolderService holderService;
    private final LogRepository logRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LocalDateTime start = LocalDateTime.now();
        Log current = new Log();
        current.setOperation("holder");
        Integer error = (Integer) delegateExecution.getVariable("ERROR");

        Map<String, String> readyHolder = holderService.getReadyHolder();
        String status = readyHolder.get("status");

        if (!status.equals("OK")) {
            current.setStatus(status);
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
