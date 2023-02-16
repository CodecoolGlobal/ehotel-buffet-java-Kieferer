package com.codecool.ehotel.service.kitchen;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.IngredientType;
import com.codecool.ehotel.model.Kitchen;
import com.codecool.ehotel.model.MealType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class KitchenManager {
    private final Kitchen kitchen;

    public KitchenManager(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public void refillKitchenIngredients(Set<Guest> guestsOfTheDay) {
        for (Guest guests : guestsOfTheDay) {
            for (MealType meal : guests.getPreferredMeals()) {
                kitchen.refillIngredients(meal.getIngredients());
                calculateExpenditureUponBuyingIngredients(meal);
            }
        }
    }

    public void calculateExpenditureUponBuyingIngredients(MealType meal) {
        int expenditure = ResourceManager.getInstance().getExpendituresOnIngredients();
        for (IngredientType ingredient : meal.getIngredients()) {
            expenditure += ingredient.getCost();
        }
        ResourceManager.getInstance().increaseExpendituresOnIngredients(expenditure);
    }

    public int createMeal(MealType meal) {
        int fressnessOfIngredients = 0;
        //Ingredients sorted from freshest to oldest for better
        orderByFreshness();
        //Check for ingredients and use them if found and return happiness points by factors
        for (IngredientType ingredient : meal.getIngredients()) {
            fressnessOfIngredients += ingredient.getFreshness();
            if (!kitchen.getAvailableIngredients().contains(ingredient)) {
                //When we can't make a meal because we are low on ingredients,
                //its gives as minus 10 happiness points.
                return -10;
            }
        }
        //Update kitchens database
        kitchen.removeAllFromAvailableIngredients(meal.getIngredients());
        //Return freshness to subtract it from happiness points.
        return fressnessOfIngredients;

    }

    public void orderByFreshness() {
        Collections.sort(kitchen.getAvailableIngredients());
    }

    public void decreaseFreshness() {
        for (IngredientType ingredient : kitchen.getAvailableIngredients()) {
            ingredient.setFreshness();
        }
    }

    public int collectExpiredIngredients() {
        int wastedMoneyOnIngredients = 0;
        List<IngredientType> expiredIngredient = new ArrayList<>();
        for (IngredientType currentIngredient : kitchen.getAvailableIngredients()) {
            switch (currentIngredient.getDurability()) {
                case SHORT -> {
                    if (currentIngredient.getFreshness() > 3) {
                        wastedMoneyOnIngredients += currentIngredient.getCost();
                        expiredIngredient.add(currentIngredient);
                    }
                }
                case MEDIUM -> {
                    if (currentIngredient.getFreshness() > 6) {
                        wastedMoneyOnIngredients += currentIngredient.getCost();
                        expiredIngredient.add(currentIngredient);
                    }
                }
            }
        }
        kitchen.removeAllFromAvailableIngredients(expiredIngredient);
        return wastedMoneyOnIngredients;
    }
}
