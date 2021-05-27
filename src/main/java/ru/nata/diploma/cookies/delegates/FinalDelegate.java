package ru.nata.diploma.cookies.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.models.Log;
import ru.nata.diploma.cookies.repositories.LogRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FinalDelegate implements JavaDelegate {
    private final LogRepository logRepository;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LocalDateTime start = LocalDateTime.now();
        Log current = new Log();
        current.setOperation("final");
        Integer error = (Integer) delegateExecution.getVariable("ERROR");
        if (error>0){
            current.setStatus("ERROR");
        }else {
            current.setStatus("OK");
        }

        LocalDateTime finish = LocalDateTime.now();
        current.setProcessId(delegateExecution.getProcessInstanceId());
        current.setStartTime(start);
        current.setFinishTime(finish);
        logRepository.save(current);
    }
}
