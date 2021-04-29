package ru.nata.diploma.cookies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nata.diploma.cookies.models.Recipe;
import ru.nata.diploma.cookies.repositories.RecipeRepository;

@RestController
@RequestMapping("/api/v1/")
public class CookiesController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/get-recipe")
    public Recipe getRecipe( @RequestParam(value = "name") String name ) {
        return recipeRepository.findByName( name );
    }

    @GetMapping("/get-all-recipe")
    public Iterable<Recipe> getAllRecipe() {
        return recipeRepository.findAll();
    }

    @PostMapping("/add-recipe")
    public Recipe addRecipe(@RequestBody Recipe recipe){
        // валидация данных
        return recipeRepository.save( recipe );
    }






}
