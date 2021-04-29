package ru.nata.diploma.cookies.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.nata.diploma.cookies.models.StartVariables;
import ru.nata.diploma.cookies.services.PrepRaw;

@RequiredArgsConstructor
public class PrepRawDelegate implements JavaDelegate {
    private final PrepRaw prepRaw;

    @Override
    public void execute( DelegateExecution delegateExecution ) {
        StartVariables start = (StartVariables) delegateExecution.getVariable( "startVariables" );
        String processId = delegateExecution.getProcessBusinessKey();//логируем это наш сквозной ключ
        StartVariables startVariables = prepRaw.action( start );
        delegateExecution.setVariable( "startVariables", startVariables );
    }
}
