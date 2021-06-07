package ru.nata.diploma.cookies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.repositories.HardwareParametersRepository;
import ru.nata.diploma.cookies.repositories.RecipeRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrepRawService {
    private final RecipeRepository recipeRepository;
//    private final HardwareParametersRepository hardwareParametersRepository;


    public Map<String, Object> action(Long idRecipe) {
        Map<String, Object> variables = new HashMap<>();

        variables.put("recipe", (recipeRepository.findById(idRecipe)
                .orElseThrow(() -> new IllegalArgumentException("Невозможно найти рецепт"))));

//        variables.put("params", (hardwareParametersRepository.findById(idParams)
//                .orElseThrow(() -> new IllegalArgumentException("Невозможно найти конфигурации для оборудования"))));

        return variables;
    }
}
