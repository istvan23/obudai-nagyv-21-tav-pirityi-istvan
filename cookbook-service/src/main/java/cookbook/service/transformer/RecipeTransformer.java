package cookbook.service.transformer;

import cookbook.domain.Unit;
import cookbook.persistence.entity.Ingredient;
import cookbook.persistence.entity.Recipe;
import cookbook.service.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeTransformer {
    public RecipeDTO convertToDto(Recipe recipe){
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setServings(recipe.getServings());
        recipeDTO.setPreparation(recipe.getPreparation());
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        recipe.getIngredients().forEach( ingredient -> {
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setId(ingredient.getId());
            ingredientDTO.setName(ingredient.getName());
            ingredientDTO.setAmount(ingredient.getAmount());
            ingredientDTO.setUnit(ingredient.getUnit());
            ingredientDTOList.add(ingredientDTO);
        });
        recipeDTO.setIngredients(ingredientDTOList);
        recipeDTO.setCategories(recipe.getCategories());
        List<CommentDTO> commentDTOList = new ArrayList<>();
        recipe.getComments().forEach(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setDescription(comment.getDescription());
            commentDTO.setTimestamp(comment.getTimestamp());
            commentDTOList.add(commentDTO);
        });
        recipeDTO.setComments(commentDTOList);
        CookDTO cookDTO = new CookDTO();
        cookDTO.setId(recipe.getUploader().getId());
        cookDTO.setUsername(recipe.getUploader().getUsername());
        recipeDTO.setUploader(cookDTO);
        return recipeDTO;
    }

    public Recipe convertToEntity(RecipeDTO recipe) {
        Recipe recipeEntity = new Recipe();
        recipeEntity.setName(recipe.getName());
        recipeEntity.setServings(recipe.getServings());
        recipeEntity.setPreparation(recipe.getPreparation());
        recipeEntity.setCategories(recipe.getCategories());
        return recipeEntity;
    }

    public RecipeDTO processNewRecipeFormToDTO(NewRecipeForm newRecipeForm){
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setName(newRecipeForm.getName());
        recipeDTO.setServings(newRecipeForm.getServings());
        recipeDTO.setPreparation(newRecipeForm.getPreparation());
        recipeDTO.setCategories(newRecipeForm.getCategories());

        List<IngredientDTO> ingredientDTOList = new ArrayList<>();

        String[] ingredientsArray = newRecipeForm.getIngredients().split("\n");
        for(String ingredient: ingredientsArray){
            String[] ingredientArray = ingredient.split(" ");
            IngredientDTO ingredientObj = new IngredientDTO();
            ingredientObj.setAmount(Double.parseDouble(ingredientArray[0]));
            ingredientObj.setUnit(Unit.valueOf(ingredientArray[1].toUpperCase()));
            ingredientObj.setName(ingredientArray[2]);
            ingredientDTOList.add(ingredientObj);
        }
        recipeDTO.setIngredients(ingredientDTOList);
        return recipeDTO;
    }


/*    public static Recipe convertToEntity(RecipeDTO recipe){
        Recipe recipeEntity = new Recipe();
        recipeEntity.setId(recipe.getId());
        recipeEntity.setName(recipe.getName());
        recipeEntity.setServings(recipe.getServings());
        recipeEntity.setPreparation(recipe.getPreparation());
        return recipeEntity;
    }*/
}
