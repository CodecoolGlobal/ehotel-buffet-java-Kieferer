package com.codecool.ehotel.model;

import java.util.List;

import static com.codecool.ehotel.model.MealDurability.*;

public enum MealType {
    SCRAMBLED_EGGS(70, SHORT, List.of(IngredientType.EGGS)),
    SUNNY_SIDE_UP(70, SHORT, List.of(IngredientType.EGGS)),
    FRIED_SAUSAGE(100, SHORT, List.of(IngredientType.SAUSAGE)),
    FRIED_BACON(70, SHORT, List.of(IngredientType.BACON)),
    PANCAKE(40, SHORT, List.of(IngredientType.EGGS, IngredientType.FLOUR, IngredientType.MILK)),
    CROISSANT(40, SHORT, List.of(IngredientType.EGGS, IngredientType.YEAST, IngredientType.MILK, IngredientType.FLOUR)),
    MASHED_POTATO(20, MEDIUM, List.of(IngredientType.POTATO)),
    MUFFIN(20, MEDIUM, List.of(IngredientType.CHOCOLATE, IngredientType.FLOUR, IngredientType.SUGAR)),
    BUN(10, MEDIUM, List.of(IngredientType.FLOUR, IngredientType.MILK, IngredientType.YEAST)),
    CEREAL(30, LONG, List.of(IngredientType.CEREAL)),
    MILK(10, LONG, List.of(IngredientType.MILK));
    private final int cost;
    private final MealDurability mealDurability;
    private final List<IngredientType> ingredients;
    MealType(int cost, MealDurability mealDurability, List<IngredientType> ingredients) {
        this.cost = cost;
        this.mealDurability = mealDurability;
        this.ingredients = ingredients;
    }
    public List<IngredientType> getIngredients() {
        return ingredients;
    }
    public int getCost() {
        return cost;
    }
    public MealDurability getDurability() {
        return mealDurability;
    }
}
