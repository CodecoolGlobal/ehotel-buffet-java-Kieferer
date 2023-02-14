package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.Collections;
import java.util.List;

public class BuffetServiceImpl implements BuffetService {


    public Buffet consumeFreshest(Buffet currentBuffet, MealType meal) {
        //placeholder variables.
        orderByFreshness(currentBuffet);
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
            meal.timestamp().replaceAll(integer -> integer + 1);
        }
    }

    @Override
    public int collectWaste(Buffet currentBuffet) {
        int collectiveWastedMoney = 0;
        for (Meal meal : currentBuffet.meals()) {
            switch (meal.mealType().getDurability()) {
                case SHORT -> {
                    List<Integer> waste = meal.timestamp().stream().filter(c -> c < 3).toList();
                    collectiveWastedMoney += waste.size() * meal.mealType().getCost();
                    meal.timestamp().removeAll(waste);
                }
                case MEDIUM -> {
                    List<Integer> waste = meal.timestamp().stream().filter(c -> c < 5).toList();
                    collectiveWastedMoney += waste.size() * meal.mealType().getCost();
                    meal.timestamp().removeAll(waste);
                }
                case LONG -> {
                    List<Integer> waste = meal.timestamp().stream().filter(c -> c < 7).toList();
                    collectiveWastedMoney += waste.size() * meal.mealType().getCost();
                    meal.timestamp().removeAll(waste);
                }
            }

        }

        return collectiveWastedMoney;
    }

    @Override
    public Buffet refill(Buffet buffet) {
        return null;
    }


}
