package com.codecool.ehotel.service.kitchen;

import com.codecool.ehotel.model.Group;
import com.codecool.ehotel.model.IngredientType;
import com.codecool.ehotel.model.Kitchen;

import java.util.ArrayList;
import java.util.List;

public class KitchenManager {
    List<IngredientType>availableIngredients = new ArrayList<>();
    List<IngredientType>consumedIngredients = new ArrayList<>();
    //Kitchen kitchen = new Kitchen(availableIngredients,consumedIngredients);


    public void refillKitchen(List<IngredientType>availableIngredients, List<Group>guestGroups, String statistic){

    }
    /*public void decreaseIngredientFreshness(){
            for (Ingredient ingredient : availableIngredients){
                ingredient.amount().replaceAll(integer -> integer + 1);
            }
    }
    public int collectExpiredIngredients() {
        int wastedMoneyOnIngredients = 0;
        for (Ingredient ingredient : availableIngredients) {

        }
        } return wastedMoneyOnIngredients;
    }*/
}
