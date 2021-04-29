package ru.nata.diploma.cookies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nata.diploma.cookies.models.StartVariables;
import ru.nata.diploma.cookies.repositories.HardwareParametersRepository;
import ru.nata.diploma.cookies.repositories.RecipeRepository;

@Service
@RequiredArgsConstructor
public class PrepRaw {
    private final RecipeRepository recipeRepository;
    private final HardwareParametersRepository hardwareParametersRepository;


    public StartVariables action( StartVariables start ) {
        StartVariables init = new StartVariables();

        init.setHardwareParameters( hardwareParametersRepository.findById( start.getParametersId() )
            .orElseThrow( () -> new IllegalArgumentException( "Невозможно найти конфигурации для оборудования" ) ) );

        init.setRecipe( recipeRepository.findById( start.getRecipeId() )
            .orElseThrow( () -> new IllegalArgumentException( "Невозможно найти рецепт" ) ) );

        return init;
    }
}
