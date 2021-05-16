package cookbook.app;

import cookbook.domain.Category;
import cookbook.domain.Comment;
import cookbook.domain.Recipe;
import cookbook.service.CookbookService;
import cookbook.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class App {
    private View view;
    private CookbookService cookbookService;

    @Autowired
    public App(View view, CookbookService cookbookService) {
        this.view = view;
        this.cookbookService = cookbookService;
    }

    public void start(){
        this.view.printWelcome();
        boolean exit = false;
        while(!exit && !cookbookService.isLoggedIn()){
            this.view.printGuestOptions();
            String input = this.view.getInput();
            if(input.equals("Q")) exit = true;
            else processMainMenuInput(input);
            while(this.cookbookService.isLoggedIn()){
                this.view.printUserOptions();
                this.processPostLoginInput();
            }
        }
    }

    private void processMainMenuInput(String text){
        switch (text) {
            case "1":
                login();
                break;
            case "2":
                this.listRecipes();
                String recipeIndex = this.view.readRecipeId();
                try {
                    this.printRecipe(Integer.parseInt(recipeIndex));
                    this.view.printGuestRecipeOptions();
                    String index = this.view.getInput();
                    Recipe recipe = this.cookbookService.getRecipes().get(Integer.parseInt(recipeIndex));
                    this.processRecipeMenuInput(recipe, index);
                }
                catch (NumberFormatException numberFormatException){
                    System.out.println("Wrong input. Only positive numbers accepted (0, 1, 2, ...)");
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException){
                    System.out.println("Wrong input. Choose only from the displayed numbers.");
                }
                catch (Exception e){
                    System.out.println("Unexpected error occurred.");
                }
                break;
            default:
                System.out.println("-- Wrong input, please try again. --");
                break;
        }
    }

    private void processPostLoginInput(){
        String input = this.view.getInput();
        switch (input) {
            case "1":
                this.newRecipe();
                break;
            case "2":
                this.listRecipes();
                String recipeIndex = this.view.readRecipeId();
                try {
                    this.printRecipe(Integer.parseInt(recipeIndex));
                    this.view.printUserRecipeOptions();
                    String index = this.view.getInput();
                    Recipe recipe = this.cookbookService.getRecipes().get(Integer.parseInt(recipeIndex));
                    this.processRecipeMenuInput(recipe, index);
                }
                catch (NumberFormatException numberFormatException){
                    System.out.println("Wrong input. Only positive numbers accepted (0, 1, 2, ...)");
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException){
                    System.out.println("Wrong input. Choose only from the displayed numbers.");
                }
                catch (Exception e){
                    System.out.println("Unexpected error occurred.");
                }
                break;
            case "3":
                this.deleteRecipe();
                break;
            case "4":
                this.logout();
                break;
            default:
                System.out.println("-- Wrong input, please try again. --");
        }
    }

    private void logout(){
        System.out.println("-- "+this.cookbookService.getCurrentUser().getUsername() +" user logged out --");
        this.cookbookService.logout();
    }

    private void login(){
        String credentials = readCredentials();
        String[] userData = credentials.split(" ");
        if ((userData.length == 2 ) && this.cookbookService.authenticate(userData[0], userData[1])){
            this.cookbookService.login(userData[0]);
            System.out.println("-- "+this.cookbookService.getCurrentUser().getUsername() +" user logged in --");
        }
        else{
            this.view.printIncorrectCredentials();
        }
    }

    private String readCredentials(){
        String credentials = "" + this.view.readUserInformation("username") + " " + this.view.readUserInformation("password");
        return credentials;
    }

    private void listRecipes(){
        List<Recipe> recipeList = this.cookbookService.getRecipes();
        this.view.printRecipes(recipeList);
    }

    private void printRecipe(int number){
        List<Recipe> recipeList = this.cookbookService.getRecipes();
        this.view.printRecipe(recipeList.get(number));
    }

    private void processRecipeMenuInput(Recipe recipe, String index){
        switch (index){
            case "1":
                this.view.printRecipeComments(recipe);
                break;
            case "2":
                if (this.cookbookService.isLoggedIn()) this.newComment(recipe);
                else this.login();
                break;
            case "Q":
                break;
            default:
                System.out.println("-- Wrong input, please try again. --");
                break;
        }
    }

    private void newComment(Recipe recipe){
        this.view.printNewCommentForm();
        String commentDescription = this.view.getInput();
        this.cookbookService.saveComment(recipe, commentDescription);
    }

    private void newRecipe(){

        try {
            Recipe recipe = this.view.printNewRecipeForm(EnumSet.allOf(Category.class), cookbookService.getCurrentUser());
            this.cookbookService.addRecipe(recipe);
        }
        catch(NumberFormatException numberFormatException){
            System.out.println("A number should have been entered here.");
        }
    }

    private void deleteRecipe(){
        System.out.println("Enter the id of the recipe you want to delete:");
        try {
            String recipeId = this.view.readRecipeId();
            this.cookbookService.deleteRecipe(recipeId);
        }
        catch (NumberFormatException numberFormatException){
            System.out.println("Wrong input. Only positive numbers accepted (0, 1, 2, ...)");
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException){
            System.out.println("Wrong input. Choose only from the displayed numbers. (Go back and see the recipes.)");
        }
        catch (Exception e){
            System.out.println("Unexpected error occurred.");
        }
    }
}
