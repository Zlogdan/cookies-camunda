package ru.nata.diploma.cookies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nata.diploma.cookies.models.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Recipe findByName( String name );
}
