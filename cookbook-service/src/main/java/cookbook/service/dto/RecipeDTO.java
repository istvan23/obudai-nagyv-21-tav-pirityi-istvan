package cookbook.service.dto;

import cookbook.domain.Category;

import java.util.List;
import java.util.Set;

public class RecipeDTO {
    private Long id;
    private String name;
    private Integer servings;
    private String preparation;
    private CookDTO uploader;
    private Set<Category> categories;
    private List<IngredientDTO> ingredients;
    private List<CommentDTO> comments;

    public RecipeDTO() {
    }

    public RecipeDTO(Long id, String name, Integer servings, String preparation, CookDTO uploader, Set<Category> categories, List<IngredientDTO> ingredients, List<CommentDTO> comments) {
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public CookDTO getUploader() {
        return uploader;
    }

    public void setUploader(CookDTO uploader) {
        this.uploader = uploader;
    }
}
