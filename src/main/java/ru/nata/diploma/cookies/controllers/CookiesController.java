package ru.nata.diploma.cookies.controllers;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.*;
import ru.nata.diploma.cookies.models.Recipe;
import ru.nata.diploma.cookies.repositories.RecipeRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CookiesController {
    private final RecipeRepository recipeRepository;
    private final RuntimeService runtimeService;

    @GetMapping("/start-process")
    public String startProcess(@RequestParam(value = "idRecipe") Long idRecipe,
                               @RequestParam(value = "idParams") Long idParams) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("idRecipe", idRecipe);
        variables.put("idParams", idParams);
        variables.put("ERROR", 0);
        return runtimeService.startProcessInstanceByKey("Cookies", variables).getProcessInstanceId();
    }

    @GetMapping("/get-recipe")
    public Optional<Recipe> getRecipe(@RequestParam(value = "id") Long id) {
        return recipeRepository.findById(id);
    }

    @GetMapping("/get-all-recipe")
    public Iterable<Recipe> getAllRecipe() {
        return recipeRepository.findAll();
    }

    @PostMapping("/add-recipe")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        // валидация данных
        return recipeRepository.save(recipe);
    }


}
