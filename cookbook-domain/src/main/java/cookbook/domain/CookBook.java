package cookbook.domain;

import java.util.List;

public class CookBook {
    private List<Recipe> recipes;
    private List<Cook> cooks;

    public CookBook() {
    }

    public CookBook(List<Recipe> recipes, List<Cook> cooks) {
        this.recipes = recipes;
        this.cooks = cooks;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Cook> getCooks() {
        return cooks;
    }

    public void setCooks(List<Cook> cooks) {
        this.cooks = cooks;
    }
}
