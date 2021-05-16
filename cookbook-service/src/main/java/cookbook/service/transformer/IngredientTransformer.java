package cookbook.service.transformer;

import cookbook.persistence.entity.Ingredient;
import cookbook.service.dto.IngredientDTO;
import org.springframework.stereotype.Component;

@Component
public class IngredientTransformer {
    public IngredientDTO convertToDto(Ingredient ingredient){
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setAmount(ingredient.getAmount());
        ingredientDTO.setUnit(ingredient.getUnit());
        return ingredientDTO;
    }
    public Ingredient convertToEntity(IngredientDTO ingredient){
        Ingredient ingredientEntity = new Ingredient();
        ingredientEntity.setName(ingredient.getName());
        ingredientEntity.setAmount(ingredient.getAmount());
        ingredientEntity.setUnit(ingredient.getUnit());
        return ingredientEntity;
    }
}
