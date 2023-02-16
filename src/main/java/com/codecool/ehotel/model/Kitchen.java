package com.codecool.ehotel.model;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {
    private final List<IngredientType> availableIngredients = new ArrayList<>();
    public List<IngredientType> getAvailableIngredients() {
        return availableIngredients;
    }
    public void refillIngredients(List<IngredientType> ingredient) {
        availableIngredients.addAll(ingredient);
    }
    public void removeFromAvailableIngredients(IngredientType ingredient) {
        availableIngredients.remove(ingredient);
    }
    public void removeAllFromAvailableIngredients(List<IngredientType> ingredientList) {
        this.availableIngredients.removeAll(ingredientList);
    }
}
