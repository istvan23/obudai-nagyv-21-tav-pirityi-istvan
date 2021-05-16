package cookbook.service.dto;

import cookbook.domain.Category;
import cookbook.validation.IngredientConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

public class NewRecipeForm {
    @NotBlank(message = "Recipe name is mandatory")
    private String name;
    @NotNull(message = "Servings is mandatory")
    @Positive(message = "Servings is a positive number")
    private Integer servings;
    @NotBlank(message = "Recipe preparation is mandatory")
    private String preparation;
    @NotEmpty(message = "At least need one category for the recipe")
    private Set<Category> categories;
    @IngredientConstraint
    private String ingredients;

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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
