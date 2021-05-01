package ru.nata.diploma.cookies.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.models.DevicesStatus;

@Service
@Getter
@Setter
public class FakeStatus {
    private DevicesStatus devicesStatus;

    public FakeStatus() {
        this.devicesStatus = new DevicesStatus()
            .setMixer( true )
            .setFurnace( true )
            .setHolder( true )
            .setAnalyzer( true );
    }

}
