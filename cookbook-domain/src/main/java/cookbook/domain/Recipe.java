package cookbook.domain;

import java.util.List;

public class Recipe {
    private Long id;
    private String name;
    private Integer servings;
    private String preparation;
    private Cook uploader;
    private List<Category> categories;
    private List<Ingredient> ingredients;
    private List<Comment> comments;

    public Recipe() {
    }

    public Recipe(Long id, String name, Integer servings, String preparation, Cook uploader, List<Category> categories, List<Ingredient> ingredients, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.preparation = preparation;
        this.uploader = uploader;
        this.categories = categories;
        this.ingredients = ingredients;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Cook getUploader() {
        return uploader;
    }

    public void setUploader(Cook uploader) {
        this.uploader = uploader;
    }
}
