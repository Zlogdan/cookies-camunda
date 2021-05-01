package ru.nata.diploma.cookies.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DevicesStatus {
    private Boolean mixer;
    private Boolean furnace;
    private Boolean holder;
    private Boolean analyzer;
}
