package cookbook.service;

import cookbook.domain.Category;
import cookbook.persistence.entity.Comment;
import cookbook.persistence.entity.Cook;
import cookbook.persistence.entity.Ingredient;
import cookbook.persistence.entity.Recipe;
import cookbook.persistence.repository.CommentRepository;
import cookbook.persistence.repository.CookRepository;
import cookbook.persistence.repository.IngredientRepository;
import cookbook.persistence.repository.RecipeRepository;
import cookbook.service.dto.CookDTO;
import cookbook.service.dto.RecipeDTO;
import cookbook.service.transformer.CommentTransformer;
import cookbook.service.transformer.CookTransformer;
import cookbook.service.transformer.IngredientTransformer;
import cookbook.service.transformer.RecipeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CookbookServiceImpl implements CookbookService{

    private Cook user;

    @Autowired
    private CookRepository cookRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CookTransformer cookTransformer;
    @Autowired
    private RecipeTransformer recipeTransformer;
    @Autowired
    private IngredientTransformer ingredientTransformer;
    @Autowired
    private CommentTransformer commentTransformer;

    @Override
    public void login(String username) {
        this.user = cookRepository.findByUsername(username).get();
    }

    @Override
    public void logout() {
        this.user = null;
    }

    @Override
    public void addRecipe(RecipeDTO recipe) {
        Recipe newRecipe = this.recipeTransformer.convertToEntity(recipe);
        List<Ingredient> ingredients = new ArrayList<>();
        recipe.getIngredients().forEach(ingredient -> ingredients.add(this.ingredientTransformer.convertToEntity(ingredient)));
        newRecipe.setIngredients(ingredients);
        newRecipe.setComments(new ArrayList<>());
        newRecipe.setUploader(this.user);

        this.user.getOwnRecipes().add(newRecipe);

        this.recipeRepository.save(newRecipe);
    }

    @Override
    public void saveComment(RecipeDTO recipe, String comment) {
        Comment newComment = new Comment();
        newComment.setDescription(comment);
        newComment.setTimestamp(LocalDateTime.now());
        Optional<Recipe> entity = this.recipeRepository.findById(recipe.getId());

        commentRepository.save(newComment);

        this.user.getComments().add(newComment);

        entity.get().getComments().add(newComment);

        recipeRepository.save(entity.get());
    }

    @Override
    public boolean isLoggedIn() {
        return this.user != null;
    }

    @Override
    public List<RecipeDTO> getRecipes() {
        List<RecipeDTO> recipeList = new ArrayList<>();
        this.recipeRepository.findAll().forEach(x -> {
            recipeList.add(recipeTransformer.convertToDto(x));
        });
        return recipeList;
    }

    @Override
    public Set<Category> getCategories() {
        return Set.of(Category.values());
    }

    @Override
    public CookDTO getCurrentUser() {
        return cookTransformer.convertToDto(this.user);
    }

    @Override
    public boolean authenticate(String username, String password) {
        for(Cook cook : this.cookRepository.findAll()){
            if (cook.getUsername().equals(username) && cook.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteRecipe(String recipe) {
        if (this.user != null){
            try {
                Recipe deletable = this.recipeRepository.findById(Long.parseLong(recipe)).get();
                this.recipeRepository.delete(deletable);
            }
            catch (Exception e){
                System.out.println("Wrong recipe id. (Warning: Recipe ID is not equal with the number on the list) ");
            }
        }
    }
}
