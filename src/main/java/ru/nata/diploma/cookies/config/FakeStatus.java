package ru.nata.diploma.cookies.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

@Service
@Data
@Accessors(chain = true)
public class FakeStatus {
    private Boolean mixerStatus = true;
    private Boolean furnaceStatus = true;
    private Boolean holderStatus = true;
    private Boolean analyzerStatus = true;


    private Boolean mixerWork = true;
    private Boolean furnaceWork = true;
    private Boolean holderWork = true;
    private Boolean analyzerWork = true;
}
