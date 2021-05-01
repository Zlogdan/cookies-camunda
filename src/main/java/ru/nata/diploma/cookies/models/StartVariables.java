package ru.nata.diploma.cookies.models;

import lombok.Data;

@Data
public class StartVariables {
    Long recipeId;
    Long parametersId;
    Recipe recipe;
    HardwareParameters hardwareParameters;
}
