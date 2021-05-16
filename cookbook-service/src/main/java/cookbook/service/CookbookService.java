package cookbook.service;

import cookbook.domain.Category;
import cookbook.service.dto.CookDTO;
import cookbook.service.dto.RecipeDTO;

import java.util.List;
import java.util.Set;

public interface CookbookService {
    void login(String username);
    void logout();
    void addRecipe(RecipeDTO recipe);
    void saveComment(RecipeDTO recipe, String comment);
    boolean isLoggedIn();
    List<RecipeDTO> getRecipes();
    Set<Category> getCategories();
    CookDTO getCurrentUser();
    boolean authenticate(String username, String password);
    void deleteRecipe(String recipe);
}
