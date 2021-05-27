package ru.nata.diploma.cookies.controllers;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nata.diploma.cookies.config.Rest;
import ru.nata.diploma.cookies.models.HardwareParameters;
import ru.nata.diploma.cookies.models.InstanceResult;
import ru.nata.diploma.cookies.models.Log;
import ru.nata.diploma.cookies.models.Recipe;
import ru.nata.diploma.cookies.repositories.HardwareParametersRepository;
import ru.nata.diploma.cookies.repositories.LogRepository;
import ru.nata.diploma.cookies.repositories.RecipeRepository;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CookiesController {
    private final RecipeRepository recipeRepository;
    private final HardwareParametersRepository hardwareParametersRepository;
    private final LogRepository logRepository;
    private final RuntimeService runtimeService;
    private final Rest rest;


    @GetMapping("/start-process")
    public Map<String, String> startProcess(@RequestParam(value = "idRecipe") Long idRecipe,
                                            @RequestParam(value = "idParams") Long idParams) {
        //проверяем количество существующих процессов
        //так как конвеер у нас 1 нельзя запускать больше 1 процесса
        String url = "http://localhost:8080/engine-rest/process-instance/count";
        Map<String, Integer> instanceCount = rest.getTemplate().getForObject(url, Map.class);
        if (instanceCount.get("count") != 0) {
            return Map.of("ERROR", "Процесс уже запущен");
        }
        Map<String, Object> variables = new HashMap<>();
        variables.put("idRecipe", idRecipe);
        variables.put("idParams", idParams);
        variables.put("ERROR", 0);
        return Map.of("ProcessInstanceId", runtimeService.startProcessInstanceByKey("Cookies", variables).getProcessInstanceId());
    }

    @GetMapping("/get-process")
    public Map<String, String> getProcess() {
        String url = "http://localhost:8080/engine-rest/history/process-instance?active=true";
        ResponseEntity<InstanceResult[]> responseInstances = rest.getTemplate().getForEntity(url, InstanceResult[].class);
        List<InstanceResult> instances = Arrays.asList(Objects.requireNonNull(responseInstances.getBody()));
        if (instances.isEmpty()) {
            return Map.of("INFO", "Нет запущеных процессов");
        } else if (instances.size() == 1) {
            return Map.of("OK", instances.get(0).getId());
        }
        return Map.of("ERROR", "Что то пошло не так");
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

    @GetMapping("/get-all-params")
    public Iterable<HardwareParameters> getAllParams() {
        return hardwareParametersRepository.findAll();
    }

    @GetMapping("/get-log")
    public List<Log> getLogsByProcessId(@RequestParam(value = "processId") String processId) {
        return logRepository.findAllByProcessId(processId);
    }

}
