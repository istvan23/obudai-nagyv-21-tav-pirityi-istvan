package cookbook.domain;

public class Ingredient {
    private double amount;
    private String name;
    private Unit unit;

    public Ingredient() {
    }

    public Ingredient(double amount, String name, Unit unit) {
        this.amount = amount;
        this.name = name;
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
