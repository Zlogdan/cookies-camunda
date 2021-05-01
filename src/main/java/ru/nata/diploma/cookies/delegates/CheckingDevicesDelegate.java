package ru.nata.diploma.cookies.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.services.CheckingDevicesService;

@Service
@RequiredArgsConstructor
public class CheckingDevicesDelegate implements JavaDelegate {
    private final CheckingDevicesService checkingDevicesService;

    @Override
    public void execute( DelegateExecution delegateExecution ) throws Exception {

        String deviceStatus = checkingDevicesService.getDeviceStatus();
        // если не ок записать в ERROR +1 или убрать ерор и работать просто со статусом 
        delegateExecution.setVariable( "deviceStatus", deviceStatus );


    }
}
