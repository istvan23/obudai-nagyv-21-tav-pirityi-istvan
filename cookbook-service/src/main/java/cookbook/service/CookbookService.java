package cookbook.service;

import cookbook.domain.Category;
import cookbook.domain.Cook;
import cookbook.domain.Recipe;

import java.util.List;
import java.util.Set;

public interface CookbookService {
    void login(String username);
    void logout();
    void addRecipe(Recipe recipe);
    void saveComment(Recipe recipe, String comment);
    boolean isLoggedIn();
    List<Recipe> getRecipes();
    Set<Category> getCategories();
    Cook getCurrentUser();
    boolean authenticate(String username, String password);
    void deleteRecipe(String recipe);
}
