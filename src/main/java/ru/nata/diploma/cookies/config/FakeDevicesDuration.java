package ru.nata.diploma.cookies.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

@Service
@Data
@Accessors(chain = true)
public class FakeDevicesDuration {

    private Integer mixerStatus = 1000;
    private Integer furnaceStatus = 1000;
    private Integer holderStatus = 1000;
    private Integer analyzerStatus = 1000;

    private Integer holderWork = 5000;
    private Integer furnaceWork = 5000;
    private Integer mixerWork = 5000;
    private Integer analyzerWork = 5000;

}
