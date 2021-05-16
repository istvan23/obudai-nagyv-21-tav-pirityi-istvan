package cookbook.service.dto;

import java.util.List;

public class CookBook {
    private List<RecipeDTO> recipes;
    private List<CookDTO> cooks;

    public CookBook() {
    }

    public CookBook(List<RecipeDTO> recipes, List<CookDTO> cooks) {
        this.recipes = recipes;
        this.cooks = cooks;
    }

    public List<RecipeDTO> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDTO> recipes) {
        this.recipes = recipes;
    }

    public List<CookDTO> getCooks() {
        return cooks;
    }

    public void setCooks(List<CookDTO> cooks) {
        this.cooks = cooks;
    }
}
