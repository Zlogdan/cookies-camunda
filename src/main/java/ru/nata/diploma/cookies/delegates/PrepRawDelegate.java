package ru.nata.diploma.cookies.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.models.Log;
import ru.nata.diploma.cookies.repositories.LogRepository;
import ru.nata.diploma.cookies.services.PrepRawService;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrepRawDelegate implements JavaDelegate {
    private final PrepRawService prepRawService;
    private final LogRepository logRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        //todo добавить проверку наличия ингридиентов на сладе
        LocalDateTime start = LocalDateTime.now();
        Log current = new Log();
        current.setOperation("prepRaw");

//        Long idParams = (Long) delegateExecution.getVariable("idParams");
        Long idRecipe = (Long) delegateExecution.getVariable("idRecipe");
        Map<String, Object> action = prepRawService.action(idRecipe);
        delegateExecution.setVariables(action);

        LocalDateTime finish = LocalDateTime.now();
        current.setProcessId(delegateExecution.getProcessInstanceId());
        current.setStartTime(start);
        current.setFinishTime(finish);
        //сохраняем рецепт в лог
        current.setComment(action.get("recipe").toString());

        logRepository.save(current);
    }
}
