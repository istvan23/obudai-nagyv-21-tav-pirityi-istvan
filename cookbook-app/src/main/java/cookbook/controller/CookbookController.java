package cookbook.controller;

import cookbook.domain.Category;
import cookbook.domain.Unit;
import cookbook.service.CookbookService;
import cookbook.service.dto.*;
import cookbook.service.transformer.RecipeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CookbookController {

    @Autowired
    @Qualifier("cookbookWebServiceImpl")
    private CookbookService cookbookService;

    @Autowired
    private RecipeTransformer recipeTransformer;

    @GetMapping("/cookbook/recipes")
    public String getRecipes(Model model){
        if(!model.containsAttribute("recipes")) {
            List<RecipeDTO> recipeDTOList = this.cookbookService.getRecipes();
            model.addAttribute("recipes", recipeDTOList);
            //model.addAttribute("recipeFilterForm", new RecipeFilterForm());
        }
        model.addAttribute("recipeFilterForm", new RecipeFilterForm());
        return "recipespage";
    }

    @PostMapping("/cookbook/recipes/filter")
    public String FilterRecipeList(Model model, RecipeFilterForm recipeFilterForm, RedirectAttributes redirectAttributes){
        List<RecipeDTO> recipeDTOList = this.cookbookService.getRecipes();
        String searchText = recipeFilterForm.getSearchText();
        if(searchText != null && searchText != ""){
            List<RecipeDTO> filteredList = new ArrayList<>();
            List<String> property = recipeFilterForm.getConstraints();
            if (property == null || property.size() == 0 || property.contains("name")){
                recipeDTOList.stream().filter(recipe -> recipe.getName().contains(searchText)).forEach(recipe -> {
                    if (!filteredList.contains(recipe)){
                        filteredList.add(recipe);
                    }
                });
            }
            if (property == null || property.size() == 0 || property.contains("category")){
                try {
                    Category category = Category.valueOf(searchText.toUpperCase());
                    recipeDTOList.stream().filter(recipe -> recipe.getCategories().contains(category)).forEach(recipe -> {
                        if (!filteredList.contains(recipe)) {
                            filteredList.add(recipe);
                        }
                    });
                }
                catch (Exception e){

                }
            }
            if (property == null || property.size() == 0 || property.contains("ingredient")){
                recipeDTOList.forEach(recipe -> recipe.getIngredients().forEach(ingredient -> {
                    if(ingredient.getName().contains(searchText) && !filteredList.contains(recipe)){
                        filteredList.add(recipe);
                    }
                }));
            }
            if (property == null || property.size() == 0 || property.contains("uploader")){
                recipeDTOList.stream().filter(recipe -> recipe.getUploader().getUsername().contains(searchText)).forEach(recipe -> {
                    if (!filteredList.contains(recipe)){
                        filteredList.add(recipe);
                    }
                });
            }
            recipeDTOList = filteredList;
        }
        redirectAttributes.addFlashAttribute("recipes", recipeDTOList);
        return "redirect:/cookbook/recipes";
    }

    @GetMapping("/cookbook/recipe/{recipeId}")
    public String getRecipeDetail(Model model, @PathVariable String recipeId)
    {
        RecipeDTO recipe = this.cookbookService.getRecipes().stream().filter(recipeDTO -> recipeDTO.getId() == Integer.parseInt(recipeId)).findFirst().get();
        model.addAttribute("recipe", recipe);
        model.addAttribute("commentObject", new NewCommentForm());
        this.cookbookService.login("fa");
        return "recipedetails";
    }



    @GetMapping("/cookbook/newRecipe")
    public String getNewRecipeForm(Model model){
        model.addAttribute("newRecipeForm", new NewRecipeForm());
        return "newrecipe";
    }

    @GetMapping("/cookbook/myRecipes")
    public String getUserRecipes(Model model){
        List<RecipeDTO> userRecipesList = this.cookbookService.getCurrentUser().getOwnRecipes();
        model.addAttribute("recipes", userRecipesList);
        return "myrecipes";
    }

    @GetMapping("/cookbook/recipe/delete/{recipeId}")
    public String deleteRecipe(@PathVariable String recipeId){
        this.cookbookService.deleteRecipe(recipeId);
        return "redirect:/cookbook/recipes";
    }

    @PostMapping("/cookbook/recipe/newComment")
    public String postNewComment(Model model, NewCommentForm commentObject){
        RecipeDTO recipe = this.cookbookService.getRecipes().stream().filter(r -> r.getId() == Integer.parseInt(commentObject.getRecipeId())).findFirst().get();
        this.cookbookService.saveComment(recipe, commentObject.getDescription());
        return "redirect:/cookbook/recipes";
    }

    @PostMapping("/cookbook/recipe/newRecipe")
    public String postNewRecipe(Model model, @Valid NewRecipeForm newRecipeForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "newrecipe";
        }
        RecipeDTO newRecipeDto = this.recipeTransformer.processNewRecipeFormToDTO(newRecipeForm);
        this.cookbookService.addRecipe(newRecipeDto);
        return "redirect:/cookbook/recipes";
    }

    @ModelAttribute("categories")
    public Map<Category, String> getClassifications(){
        return Category.toMap();
    }
}
