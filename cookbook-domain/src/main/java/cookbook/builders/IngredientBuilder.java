package cookbook.builders;

import cookbook.domain.Ingredient;
import cookbook.domain.Unit;

public class IngredientBuilder {
    private double amount;
    private String name;
    private Unit unit;

    public IngredientBuilder() {
    }

    public IngredientBuilder setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public IngredientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public IngredientBuilder setUnit(Unit unit) {
        this.unit = unit;
        return this;
    }

    public Ingredient getIngredient(){
        return new Ingredient(this.amount, this.name, this.unit);
    }
}
