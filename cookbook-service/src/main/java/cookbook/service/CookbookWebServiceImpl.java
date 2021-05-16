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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CookbookWebServiceImpl implements CookbookService {

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

    }

    @Override
    @Deprecated(forRemoval = true)
    public void logout() {

    }

    @Override
    public void addRecipe(RecipeDTO recipe) {
        CookDTO cookDTO = getCurrentUser();

        Cook cook = this.cookRepository.findByUsername(cookDTO.getUsername()).get();
        Recipe newRecipe = this.recipeTransformer.convertToEntity(recipe);
        List<Ingredient> ingredients = new ArrayList<>();
        recipe.getIngredients().forEach(ingredient -> ingredients.add(this.ingredientTransformer.convertToEntity(ingredient)));
        newRecipe.setIngredients(ingredients);
        newRecipe.setComments(new ArrayList<>());
        newRecipe.setUploader(cook);

        cook.getOwnRecipes().add(newRecipe);

        this.recipeRepository.save(newRecipe);
    }

    @Override
    public void saveComment(RecipeDTO recipe, String comment) {
        CookDTO cookDTO = getCurrentUser();
        Comment newComment = new Comment();
        newComment.setDescription(comment);
        newComment.setTimestamp(LocalDateTime.now());
        Optional<Recipe> recipeEntity = this.recipeRepository.findById(recipe.getId());

        commentRepository.save(newComment);

        Cook cook = this.cookRepository.findByUsername(cookDTO.getUsername()).get();

        cook.getComments().add(newComment);

        recipeEntity.get().getComments().add(newComment);

        recipeRepository.save(recipeEntity.get());
    }

    @Override
    @Deprecated(forRemoval = true)
    public boolean isLoggedIn() {
        return false;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Cook currentCook = this.cookRepository.findByUsername(currentPrincipalName).get();
        CookDTO currentCookDto = this.cookTransformer.convertToDto(currentCook);
        currentCook.getOwnRecipes().forEach(recipe -> currentCookDto.getOwnRecipes().add(this.recipeTransformer.convertToDto(recipe)));
        return currentCookDto;

    }

    @Override
    @Deprecated(forRemoval = true)
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
        CookDTO cookDTO = getCurrentUser();
        if (cookDTO != null){

            try {
                Recipe deletable = this.recipeRepository.findById(Long.parseLong(recipe)).get();
                this.recipeRepository.delete(deletable);
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
