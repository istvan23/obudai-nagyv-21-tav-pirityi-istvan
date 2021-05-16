package cookbook.view;

import cookbook.domain.Category;
import cookbook.domain.Unit;
import cookbook.service.dto.CookDTO;
import cookbook.service.dto.IngredientDTO;
import cookbook.service.dto.RecipeDTO;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class ViewImpl implements View{
    private static Scanner scanner = new Scanner(System.in);
    @Override
    public RecipeDTO printNewRecipeForm(Set<Category> categories, CookDTO uploader) {
        System.out.println("What's the name of your dish?");
        String name = scanner.nextLine();
        while(name.isEmpty()){
            System.out.println("Please try again!");
            System.out.println("What's the name of your dish?");
            name = scanner.nextLine();
        }
        System.out.println("How many people does this dish serve?");
        Integer serve = Integer.parseInt(scanner.nextLine());
        System.out.println("What kind of ingredients do you need?");
        List<IngredientDTO> ingredients = new ArrayList<>();
        boolean moreIngredient = false;
        do {
            try {
                int amount = Integer.parseInt(scanner.nextLine());
                Unit unit = Unit.valueOf(scanner.nextLine().toUpperCase());
                String ingredientName = scanner.nextLine();

                IngredientDTO ingredient = new IngredientDTO();
                ingredient.setAmount(amount);
                ingredient.setName(ingredientName);
                ingredient.setUnit(unit);

                ingredients.add(ingredient);
                System.out.println("Add another? (Y/N)");
                char repeat;

                repeat = scanner.nextLine().charAt(0);
                moreIngredient = Character.toUpperCase(repeat) == 'Y';
            } catch (Exception e) {
                System.out.println("Please try again. Correct order is: Amount of ingredient, Unit, Name\n" +
                        "(Acceptable units: GRAM, KILOGRAM, LITER, MILLILITER, PIECE)");
                moreIngredient = true;
            }

        }while(moreIngredient);
        System.out.println("How do you make this dish? (type 'C' to continue)");
        String input = scanner.nextLine();
        String preparation = "";
        if (input.toLowerCase() != "c") preparation = input;
        System.out.println("How would you categorize this dish? (type 'C' to continue)");
        input = "";
        Set<Category> recipeCategories = new HashSet<>();
        do {
            int index = 0;
            for(Category category : categories){
                System.out.println(index+": "+category.name());
                index++;
            }
            try {
                input = scanner.nextLine();
                if (!input.equals("C")) {
                    index = Integer.parseInt(input);
                    Category category = (Category) (categories.toArray())[index];
                    categories.remove(category);
                    recipeCategories.add(category);
                }
            }catch (Exception e){
                System.out.println("Wrong input, please try again!");
            }
        }while(!input.equals("C") && !categories.isEmpty());
        RecipeDTO newRecipe = new RecipeDTO();
        newRecipe.setName(name);
        newRecipe.setPreparation(preparation);
        newRecipe.setServings(serve);
        newRecipe.setCategories(recipeCategories);
        newRecipe.setIngredients(ingredients);
        newRecipe.setUploader(uploader);
        newRecipe.setComments(new ArrayList<>());

        return newRecipe;
    }

    @Override
    public void printWelcome() {
        System.out.println("-- Application started --");
        System.out.println("-- Welcome to the Cookbook application! --");
    }

    @Override
    public void printUserOptions() {
        System.out.println("1: Create new recipe.");
        System.out.println("2: List existing recipes.");
        System.out.println("3: Delete recipe.");
        System.out.println("4: Log out.");
    }

    @Override
    public void printGuestOptions() {
        System.out.println("1: Log in.");
        System.out.println("2: Browse existing recipes.");
        System.out.println("Q: Exit the application.");
    }

    @Override
    public void printRecipe(RecipeDTO recipe) {
        System.out.println("\t\t -- Recipe: "+recipe.getName()+" --");
        System.out.println("Recipe ID:\t"+recipe.getId());
        System.out.println("Uploader:\t"+recipe.getUploader().getUsername());
        System.out.println("Servings:\t"+recipe.getServings());
        System.out.println("Ingredients:");
        for(IngredientDTO ingredient : recipe.getIngredients()){
            System.out.println("\t"+ingredient.getAmount()+" "+ingredient.getUnit()+" "+ingredient.getName());
        }
        System.out.println("Preparation:\n\t"+recipe.getPreparation());
        System.out.println("Categories:");
        for(Category category : recipe.getCategories()){
            System.out.println("\t"+category);
        }
    }

    @Override
    public void printUserRecipeOptions() {
        System.out.println("1: See comments");
        System.out.println("2: Write comment");
        System.out.println("Q: Go back");
    }

    @Override
    public void printGuestRecipeOptions() {
        System.out.println("1: See comments");
        System.out.println("2: -- Log in to write comments --");
        System.out.println("Q: Go back");
    }

    @Override
    public void printRecipeComments(RecipeDTO recipe) {
        for(int i = 0; i < recipe.getComments().size(); i++){
            System.out.println(i+".\t"+recipe.getComments().get(i).getTimestamp());
            System.out.println(recipe.getComments().get(i).getDescription());
        }
    }

    @Override
    public void printRecipes(List<RecipeDTO> recipes) {
        for(int i = 0; i < recipes.size(); i++){
            System.out.println(i+": "+recipes.get(i).getName());
        }
    }

    @Override
    public void printNewCommentForm() {
        System.out.println("Write your comment. (Single line)");
    }

    @Override
    public void printNotAuthenticated() {
        System.out.println("You are not authenticated.");
    }

    @Override
    public void printLogout() {
        System.out.println("-- Application ended --");
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    @Override
    public String readUserInformation(String text) {
        System.out.println("Give me your "+text+":");
        return scanner.nextLine();
    }

    @Override
    public void printIncorrectCredentials() {
        System.out.println("This credentials are incorrect. Please try again!");
    }

    @Override
    public String readRecipeId() {
        return scanner.nextLine();
    }
}
