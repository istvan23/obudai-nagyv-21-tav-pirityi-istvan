package cookbook.builders;

import cookbook.domain.*;

import java.util.List;

public class RecipeBuilder {
    private static long idCounter = 0;
    private Long id;
    private String name;
    private Integer servings;
    private String preparation;
    private Cook uploader;
    private List<Category> categories;
    private List<Ingredient> ingredients;
    private List<Comment> comments;

    public RecipeBuilder() {
    }

    public RecipeBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public RecipeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RecipeBuilder setServings(Integer servings) {
        this.servings = servings;
        return this;
    }

    public RecipeBuilder setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public RecipeBuilder setUploader(Cook uploader) {
        this.uploader = uploader;
        return this;
    }

    public RecipeBuilder setCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

    public RecipeBuilder setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public RecipeBuilder setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Recipe getRecipe(){
        return new Recipe(id, name, servings, preparation, uploader, categories, ingredients, comments);
    }
}
