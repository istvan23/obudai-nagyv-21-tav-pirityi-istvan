package cookbook.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IngredientValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IngredientConstraint {
    String message() default "Invalid ingredient input.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
