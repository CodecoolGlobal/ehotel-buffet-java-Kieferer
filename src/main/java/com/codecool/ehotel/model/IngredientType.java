package com.codecool.ehotel.model;

import static com.codecool.ehotel.model.MealDurability.*;

public enum IngredientType {
    EGGS(5, SHORT),
    SAUSAGE(15, SHORT),
    BACON(15, SHORT),
    YEAST(5, SHORT),
    FLOUR(10, SHORT),
    SUGAR(10, LONG),
    CHOCOLATE(20, SHORT),
    SALT(5, LONG),
    POTATO(10, LONG),
    CEREAL(7, MEDIUM),
    MILK(10, LONG);

    private final int cost;
    private final MealDurability mealDurability;
    private int freshness;

    IngredientType(int cost, MealDurability mealDurability) {
        this.cost = cost;
        this.mealDurability = mealDurability;
        this.freshness = 0;
    }

    public int getCost() {
        return cost;
    }

    public MealDurability getDurability() {
        return mealDurability;
    }

    public int getFreshness() {
        return freshness;
    }

    public void setFreshness() {
        this.freshness++;
    }


}
