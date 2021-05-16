package cookbook.view;

import cookbook.domain.Category;
import cookbook.service.dto.CookDTO;
import cookbook.service.dto.RecipeDTO;

import java.util.List;
import java.util.Set;

public interface View {
    RecipeDTO printNewRecipeForm(Set<Category> categories, CookDTO uploader);
    void printWelcome();
    void printUserOptions();
    void printGuestOptions();
    void printRecipe(RecipeDTO recipe);
    void printUserRecipeOptions();
    void printGuestRecipeOptions();
    void printRecipeComments(RecipeDTO recipe);
    void printRecipes(List<RecipeDTO> recipes);
    void printNewCommentForm();
    void printNotAuthenticated();
    void printLogout();
    String getInput();
    String readUserInformation(String text);
    void printIncorrectCredentials();
    String readRecipeId();
}
