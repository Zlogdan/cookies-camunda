package ru.nata.diploma.cookies.models;

import lombok.Data;

@Data
public class StartVariables {
    Long recipeId;
    Long ParametersId;
    Recipe recipe;
    HardwareParameters hardwareParameters;
}
