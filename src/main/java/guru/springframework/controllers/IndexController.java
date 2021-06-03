package guru.springframework.controllers;

import guru.springframework.services.RecipeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {
    private final RecipeServices recipeServices;

    public IndexController(RecipeServices recipeServices) {
        this.recipeServices = recipeServices;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeServices.getRecipes());
        return "index";
    }
}
