package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.Collections;
import java.util.List;

public class consumeFreshest implements BuffetService {

    public boolean consumeFreshest(Buffet currentBuffet, MealType meal) {
       //placeholder variables.
        boolean foundMeal = false;
        orderByFreshness(currentBuffet);
        List<Meal> currentBuffetMeals = currentBuffet.meals();
        for (Meal currentBuffetMeal : currentBuffetMeals) {
            if (currentBuffetMeal.mealType().equals(meal) && currentBuffetMeal.timestamp().size() > 0) {
                currentBuffetMeal.timestamp().remove(0);
                decreaseFreshness(currentBuffet);
                System.out.println("One piece of: " + currentBuffetMeal.mealType() + " has been consumed.");
                foundMeal = true;
            }
            else if (currentBuffetMeal.mealType().equals(meal)) {
            decreaseFreshness(currentBuffet);
            System.out.println("There is no more " + currentBuffetMeal.mealType() + " has been consumed.");
            }
        }
        return foundMeal;
    }
    public void orderByFreshness(Buffet buffet){
        for (Meal meal: buffet.meals()) {
            Collections.sort(meal.timestamp());
        }
    }
    public void decreaseFreshness(Buffet buffet){
        for (Meal meal: buffet.meals()) {
            for (int timestamp:meal.timestamp()) {
                timestamp++;
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
