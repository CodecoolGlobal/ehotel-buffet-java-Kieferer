package com.codecool.ehotel.service.kitchen;

import com.codecool.ehotel.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KitchenManager{
    private Kitchen kitchen;

    public KitchenManager(Kitchen kitchen) {
        this.kitchen = kitchen;
    }
    public void refillKitchen(Kitchen kitchen, List<Group> guestGroups, String statistic) {

    }
    public void createMeal(MealType meal) {
        for (IngredientType ingredient: meal.getIngredients()
             ) {
            if(!kitchen.getAvailableIngredients().contains(ingredient)){
                return;
            }
        }
        kitchen.addAllToConsumedIngredients(meal.getIngredients());
        //TODO calculation for freshness.



    }
    public void orderByFreshness(Kitchen kitchen){
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
            kitchen.addAllToConsumedIngredients(expiredIngredient);
            kitchen.removeAllFromAvailableIngredients(expiredIngredient);
            return wastedMoneyOnIngredients;
    }


}
