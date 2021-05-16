package cookbook.validation;

import cookbook.domain.Unit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IngredientValidation implements ConstraintValidator<IngredientConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value != "") {
            String[] ingredients = value.split("\n");
            for(String ingredient : ingredients){
                String[] i = ingredient.split(" ");
                if (i.length != 3) return false;
                try{
                    Double.parseDouble(i[0]);
                    Unit.valueOf(i[1].toUpperCase());
                }catch(Exception e) {
                    return false;
                }

            }

        } else return false;
        return true;
    }
}
