package com.codecool.ehotel.model;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {
    private final List<IngredientType> availableIngredients = new ArrayList<>();
    private final List<IngredientType> consumedIngredients = new ArrayList<>();
    public List<IngredientType> availableIngredients() { return availableIngredients; }
    public List<IngredientType> consumedIngredients() { return consumedIngredients; }

    public void addIngredients(IngredientType ingredient){
        availableIngredients.add(ingredient);
    }
    public void addConsumedIngredients(IngredientType ingredient){
        consumedIngredients.add(ingredient);
    }
    public void removeFromAvailableIngredients(IngredientType ingredient) { availableIngredients.remove(ingredient); }
    public void removeAllFromAvailableIngredients(List<IngredientType> ingredientList){
        this.availableIngredients.removeAll(ingredientList);
    }

}
