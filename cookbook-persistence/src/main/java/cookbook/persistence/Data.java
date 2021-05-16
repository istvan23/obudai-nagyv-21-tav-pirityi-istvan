package cookbook.persistence;

import cookbook.builders.CookBuilder;
import cookbook.builders.IngredientBuilder;
import cookbook.builders.RecipeBuilder;
import cookbook.domain.*;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Data {
    private List<Cook> cooks;
    private List<Recipe> recipes;

    private final String COOKS_TEXT_FILE_PATH = "data/cooks.txt";
    private final String RECIPES_TEXT_FILE_PATH = "data/recipes.txt";
    private final String COMMENTS_TEXT_FILE_PATH = "data/comments.txt";

    private BufferedReader cooksFileReader;
    private PrintWriter cooksFileWriter;

    private BufferedReader recipesFileReader;
    private PrintWriter recipesFileWriter;

    private BufferedReader commentsFileReader;
    private PrintWriter commentsFileWriter;


    public Data() {
        this.cooks = new ArrayList<>();
        this.recipes = new ArrayList<>();

        try {

            boolean dataExist = (new File(COOKS_TEXT_FILE_PATH)).exists();
            if(!dataExist) initData();

            this.cooksFileReader = new BufferedReader(new FileReader(COOKS_TEXT_FILE_PATH));
            this.recipesFileReader = new BufferedReader(new FileReader(RECIPES_TEXT_FILE_PATH));
            this.commentsFileReader = new BufferedReader(new FileReader(COMMENTS_TEXT_FILE_PATH));

            loadData();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Long generateId(Type type){
        Long newId = null;
        if (!this.cooks.isEmpty() && type.getTypeName() == Cook.class.getName()){
            newId = this.cooks.stream().sorted(Comparator.comparing(Cook::getId).reversed()).findFirst().get().getId() + 1;
        }
        else if (!this.recipes.isEmpty() && type.getTypeName() == Recipe.class.getName()){
            newId = this.recipes.stream().sorted(Comparator.comparing(Recipe::getId).reversed()).findFirst().get().getId() + 1;
        }
        else if ( this.recipes.stream().filter(recipe -> !recipe.getComments().isEmpty()).count() > 0 && type.getTypeName() == Comment.class.getName()){
            newId = this.recipes.stream()
                    .flatMap(x -> x.getComments().stream()).collect(Collectors.toList())
                    .stream().sorted(Comparator.comparing(Comment::getId).reversed()).findFirst().get().getId() + 1;
        }
        else{
            newId = 0l;
        }
        return newId;
    }

    private void initData(){

    }

    private void loadData(){
        String line = null;
        try {
            line = this.cooksFileReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(line != null){
            String[] raw_data = line.split(" ");
            CookBuilder cookBuilder = new CookBuilder();
            Cook cook = cookBuilder
                    .setId(Long.parseLong(raw_data[0]))
                    .setUsername(raw_data[1])
                    .setPassword(raw_data[2])
                    .setComments(new ArrayList<>())
                    .setOwnRecipes(new ArrayList<>())
                    .getCook();
            this.cooks.add(cook);
            try {
                line = this.cooksFileReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            readRecipeData();
            readComments();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readRecipeData() throws Exception {
        String line = null;
        RecipeBuilder recipeBuilder = new RecipeBuilder();
        try {
            line = this.recipesFileReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(line != null && line.contains("[id]")) {
            if (line.contains("[id]")) {
                // id value
                line = this.recipesFileReader.readLine();
                recipeBuilder.setId(Long.parseLong(line));
                line = this.recipesFileReader.readLine();
            } else throw new Exception("Invalid file format or recipe id is missing");


            if (line.contains("[user_id]")) {
                line = this.recipesFileReader.readLine();
                String finalLine = line;
                recipeBuilder.setUploader(this.cooks.stream().filter(x -> x.getId() == Long.parseLong(finalLine)).findFirst().get());
                line = this.recipesFileReader.readLine();
            } else throw new Exception("Invalid file format or recipe creator id is missing.");

            if (line.contains("[name]")) {
                line = this.recipesFileReader.readLine();
                recipeBuilder.setName(line);
                line = this.recipesFileReader.readLine();
            } else throw new Exception("Invalid file format or recipe name is missing.");

            if (line.contains("[ingredients]")) {
                List<Ingredient> ingredients = new ArrayList<>();
                while (!(line = this.recipesFileReader.readLine()).contains("[preparation]") && line != null) {
                    String[] rawInput = line.split(" ");
                    Ingredient ingredient = new Ingredient(Double.parseDouble(rawInput[0]), rawInput[2], Unit.valueOf(rawInput[1]));
                    ingredients.add(ingredient);
                }
                recipeBuilder.setIngredients(ingredients);
            } else throw new Exception("Invalid file format or ingredients is missing.");

            if (line.contains("[preparation]")) {
                line = this.recipesFileReader.readLine();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(line);
                line = this.recipesFileReader.readLine();
                while(!line.contains("[servings]")){
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                    line = this.recipesFileReader.readLine();
                }
                recipeBuilder.setPreparation(stringBuilder.toString());
            } else throw new Exception("Invalid file format or recipe name is missing.");

            if (line.contains("[servings]")) {
                line = this.recipesFileReader.readLine();
                recipeBuilder.setServings(Integer.parseInt(line));
                line = this.recipesFileReader.readLine();
            } else throw new Exception("Invalid file format or servings is missing.");

            if (line.contains("[categories]")) {
                List<Category> categories = new ArrayList<>();
                line = this.recipesFileReader.readLine();
                while (line != null && !line.contains("[id]")) {
                    Category category = Category.valueOf(line);
                    categories.add(category);
                    line = this.recipesFileReader.readLine();
                }
                recipeBuilder.setCategories(categories);
            } else throw new Exception("Invalid file format or ingredients is missing.");
            recipeBuilder.setComments(new ArrayList<>());
            Recipe recipe = recipeBuilder.getRecipe();
            recipe.getUploader().getOwnRecipes().add(recipe);
            this.recipes.add(recipe);
        }
    }

    private void readComments() throws IOException {
        String line = this.commentsFileReader.readLine();
        while(line != null && !line.isEmpty()){
            String[] rawCommentData = line.split(" ");
            String description = rawCommentData[4];
            if (rawCommentData.length != 4){
                for (int i = 5; i <= rawCommentData.length - 1; i++){
                    description += " " + rawCommentData[i];
                }
            }
            Comment comment = new Comment(Long.parseLong(rawCommentData[0]), description, LocalDateTime.parse(rawCommentData[3]));
            this.cooks.stream().filter(cook -> cook.getId() == Long.parseLong(rawCommentData[2])).findFirst().get().getComments().add(comment);
            this.recipes.stream().filter(recipe -> recipe.getId() == Long.parseLong(rawCommentData[1])).findFirst().get().getComments().add(comment);
            line = this.commentsFileReader.readLine();
        }
    }

    @PreDestroy
    private void end(){
        try {
            this.cooksFileReader.close();
            this.recipesFileReader.close();
            this.commentsFileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        saveData();

        this.cooksFileWriter.close();
        this.recipesFileWriter.close();
        this.commentsFileWriter.close();
    }

    private void saveData(){
        try {
            this.cooksFileWriter = new PrintWriter(new FileWriter(COOKS_TEXT_FILE_PATH));
            this.recipesFileWriter = new PrintWriter(new FileWriter(RECIPES_TEXT_FILE_PATH));
            this.commentsFileWriter = new PrintWriter(new FileWriter(COMMENTS_TEXT_FILE_PATH));
            saveCooks();
            saveRecipes();
            saveComments();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCooks(){
        this.cooks.forEach(cook -> this.cooksFileWriter.println(
                cook.getId().toString() + " " +
                cook.getUsername() + " " +
                cook.getPassword()));
    }

    private void saveRecipes(){
        this.recipes.forEach(recipe -> {
            this.recipesFileWriter.println("[id]");
            this.recipesFileWriter.println(recipe.getId());
            this.recipesFileWriter.println("[user_id]");
            this.recipesFileWriter.println(recipe.getUploader().getId());
            this.recipesFileWriter.println("[name]");
            this.recipesFileWriter.println(recipe.getName());
            this.recipesFileWriter.println("[ingredients]");
            recipe.getIngredients().forEach(ingredient -> this.recipesFileWriter.println(
                            ingredient.getAmount() + " " +
                            ingredient.getUnit() + " " +
                            ingredient.getName()));
            this.recipesFileWriter.println("[preparation]");
            this.recipesFileWriter.println(recipe.getPreparation());
            this.recipesFileWriter.println("[servings]");
            this.recipesFileWriter.println(recipe.getServings());
            this.recipesFileWriter.println("[categories]");
            recipe.getCategories().forEach(category -> this.recipesFileWriter.println(category.toString()));
        });

    }

    private void saveComments(){
        this.cooks.forEach(cook -> {
            cook.getComments().forEach(comment -> {
                Long recipeId = this.recipes.stream().filter(x -> x.getComments().contains(comment)).findFirst().get().getId();
                this.commentsFileWriter.println(comment.getId() + " " + recipeId + " " + cook.getId() + " " + comment.getTimestamp() + " " + comment.getDescription());
            });
        });
    }

    public List<Cook> getCooks() {
        return cooks;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
