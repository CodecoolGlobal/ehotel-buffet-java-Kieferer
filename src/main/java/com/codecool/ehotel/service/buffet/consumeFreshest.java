package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.Collections;
import java.util.List;

public class consumeFreshest implements BuffetService {

    public Buffet consumeFreshest(Buffet currentBuffet, MealType meal) {
       //placeholder variables.
        orderByFreshness(currentBuffet);
       // boolean foundMeal = false;
        List<Meal> currentBuffetMeals = currentBuffet.meals();
        for (Meal currentBuffetMeal : currentBuffetMeals) {

            if (currentBuffetMeal.mealType().equals(meal) && currentBuffetMeal.timestamp().size() > 0) {
                currentBuffetMeal.timestamp().remove(0);
                decreaseFreshness(currentBuffet);
                System.out.println("One piece of: " + currentBuffetMeal.mealType() + " has been consumed.");
            }

            else if (currentBuffetMeal.mealType().equals(meal)) {
            decreaseFreshness(currentBuffet);
            System.out.println("There is no more " + currentBuffetMeal.mealType() + " has been consumed.");
            }

        }
        return currentBuffet;
    }
    public void orderByFreshness(Buffet buffet){
        for (Meal meal: buffet.meals()) {
            Collections.sort(meal.timestamp());
        }
    }
    public void decreaseFreshness(Buffet buffet){
        for (Meal meal: buffet.meals()) {
            for (int i = 0; i < meal.timestamp().size();i++) {
                meal.timestamp().set(i,meal.timestamp().get(i)+1);
            }
        }
    }
    @Override
    public void collectWaste(Buffet currentBuffet) {

    }

    @Override
    public Buffet refill(Buffet buffet) {
        return null;
    }
}
