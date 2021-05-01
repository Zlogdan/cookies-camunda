package ru.nata.diploma.cookies.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.models.StartVariables;
import ru.nata.diploma.cookies.services.PrepRaw;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrepRawDelegate implements JavaDelegate {
    private final PrepRaw prepRaw;

    @Override
    public void execute( DelegateExecution delegateExecution ) {
        Long idParams = (Long) delegateExecution.getVariable( "idParams" );
        Long idRecipe = (Long) delegateExecution.getVariable( "idRecipe" );
        String processId = delegateExecution.getProcessInstanceId(); //логируем это наш сквозной ключ
        delegateExecution.setVariables( prepRaw.action( idRecipe, idParams ) );
    }
}
