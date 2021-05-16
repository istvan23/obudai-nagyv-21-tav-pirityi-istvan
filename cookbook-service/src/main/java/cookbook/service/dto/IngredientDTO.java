package cookbook.service.dto;

import cookbook.domain.Unit;

public class IngredientDTO {
    private Long id;
    private double amount;
    private String name;
    private Unit unit;

    public IngredientDTO() {
    }

    public IngredientDTO(Long id, double amount, String name, Unit unit) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
