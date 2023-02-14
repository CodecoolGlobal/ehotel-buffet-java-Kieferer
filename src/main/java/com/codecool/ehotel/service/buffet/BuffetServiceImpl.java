package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.Collections;
import java.util.List;

public class BuffetServiceImpl implements BuffetService {


    public int consumeFreshest(Buffet currentBuffet, MealType meal) {
        orderByFreshness(currentBuffet);
        for (Meal currentBuffetMeal : currentBuffet.meals()) {
            if (currentBuffetMeal.mealType().equals(meal) && currentBuffetMeal.timestamp().size() > 0) {
                currentBuffetMeal.timestamp().remove(0);
                //System.out.println("One piece of: " + currentBuffetMeal.mealType() + " has been consumed.");
                return 0;
            }
            else if (currentBuffetMeal.mealType().equals(meal)) {
                //System.out.println("There is no more " + currentBuffetMeal.mealType() + " has been consumed.");
                return 1;
            }
        }
        return 1;
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
    public int collectWaste(Buffet buffet) {
        int collectiveWastedMoney = 0;
        for (Meal meal : buffet.meals()) {
            switch (meal.mealType().getDurability()) {
                case SHORT -> {
                    List<Integer> waste = meal.timestamp().stream().filter(c -> c > 2).toList();
                    collectiveWastedMoney += waste.size() * meal.mealType().getCost();
                    meal.timestamp().removeAll(waste);
                }
                case MEDIUM -> {
                    List<Integer> waste = meal.timestamp().stream().filter(c -> c > 5).toList();
                    collectiveWastedMoney += waste.size() * meal.mealType().getCost();
                    meal.timestamp().removeAll(waste);
                }
                case LONG -> {
                    List<Integer> waste = meal.timestamp().stream().filter(c -> c > 10).toList();
                    collectiveWastedMoney += waste.size() * meal.mealType().getCost();
                    meal.timestamp().removeAll(waste);
                }
            }

        }
        return collectiveWastedMoney;
    }

    @Override
    public void refill(Buffet buffet) {
    }


}
