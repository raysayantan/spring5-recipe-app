package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeServices {
    public Set<Recipe> getRecipes();
}
