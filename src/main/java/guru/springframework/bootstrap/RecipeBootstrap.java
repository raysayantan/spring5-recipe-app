package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipies());
    }

    private List<Recipe> getRecipies(){
        List<Recipe> recipies = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUnitOfMeasure = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUnitOfMeasure.isPresent()){
            throw new RuntimeException("Unit Of Measure not found");
        }

        Optional<UnitOfMeasure> tblspnUnitOfMeasure = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tblspnUnitOfMeasure.isPresent()){
            throw new RuntimeException("Unit Of Measure not found");
        }

        Optional<UnitOfMeasure> tspUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!tspUnitOfMeasure.isPresent()){
            throw new RuntimeException("Unit Of Measure not found");
        }

        Optional<UnitOfMeasure> dashUnitOfMeasure = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUnitOfMeasure.isPresent()){
            throw new RuntimeException("Unit Of Measure not found");
        }

        Optional<UnitOfMeasure> pntUnitOfMeasure = unitOfMeasureRepository.findByDescription("Pint");
        if(!pntUnitOfMeasure.isPresent()){
            throw new RuntimeException("Unit Of Measure not found");
        }

        Optional<UnitOfMeasure> cupUnitOfMeasure = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUnitOfMeasure.isPresent()){
            throw new RuntimeException("Unit Of Measure not found");
        }

        UnitOfMeasure eachUom = eachUnitOfMeasure.get();
        UnitOfMeasure tblUom = tblspnUnitOfMeasure.get();
        UnitOfMeasure tspUom = tspUnitOfMeasure.get();
        UnitOfMeasure dashUom = dashUnitOfMeasure.get();
        UnitOfMeasure pintUom = pntUnitOfMeasure.get();
        UnitOfMeasure cupUom = cupUnitOfMeasure.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Category not found");
        }

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if(!italianCategoryOptional.isPresent()){
            throw new RuntimeException("Category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Category not found");
        }

        Optional<Category> fastfoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
        if(!fastfoodCategoryOptional.isPresent()){
            throw new RuntimeException("Category not found");
        }

        Category americanCat = americanCategoryOptional.get();
        Category mexicanCat = mexicanCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Some Dummy Direction I am giving here for place holder");

        Notes guacNote = new Notes();
        guacNote.setRecipeNotes("This is a place holder note");
        guacNote.setRecipe(guacRecipe);

        guacRecipe.setNotes(guacNote);

        guacRecipe.getIngredients().add(new Ingredient("Ripe Avocado", new BigDecimal(2), eachUom));

        guacRecipe.getCategories().add(americanCat);
        guacRecipe.getCategories().add(mexicanCat);

        recipies.add(guacRecipe);

        return recipies;
    }
}
