package ru.nata.diploma.cookies.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.models.Log;
import ru.nata.diploma.cookies.repositories.LogRepository;
import ru.nata.diploma.cookies.services.PrepRaw;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PrepRawDelegate implements JavaDelegate {
    private final PrepRaw prepRaw;
    private final LogRepository logRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        LocalDateTime start = LocalDateTime.now();
        Log current = new Log();
        current.setOperation("prepRaw");

        Long idParams = (Long) delegateExecution.getVariable("idParams");
        Long idRecipe = (Long) delegateExecution.getVariable("idRecipe");
        delegateExecution.setVariables(prepRaw.action(idRecipe, idParams));

        LocalDateTime finish = LocalDateTime.now();
        current.setProcessId(delegateExecution.getProcessInstanceId());
        current.setStartTime(start);
        current.setFinishTime(finish);
        current.setComment("idRecipe: "+idRecipe+", idParams: "+idParams);

        logRepository.save(current);
    }
}
