package cookbook.view;

import cookbook.domain.Category;
import cookbook.domain.Cook;
import cookbook.domain.Recipe;

import java.util.List;
import java.util.Set;

public interface View {
    Recipe printNewRecipeForm(Set<Category> categories, Cook uploader);
    void printWelcome();
    void printUserOptions();
    void printGuestOptions();
    void printRecipe(Recipe recipe);
    void printUserRecipeOptions();
    void printGuestRecipeOptions();
    void printRecipeComments(Recipe recipe);
    void printRecipes(List<Recipe> recipes);
    void printNewCommentForm();
    void printNotAuthenticated();
    void printLogout();
    String getInput();
    String readUserInformation(String text);
    void printIncorrectCredentials();
    String readRecipeId();
}
