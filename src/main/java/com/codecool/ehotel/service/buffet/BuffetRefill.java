package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.*;

public class BuffetRefill implements BuffetService{
    @Override
    public Buffet consumeFreshest(Buffet currentBuffet, MealType meal) {
        return currentBuffet;
    }

    @Override
    public int collectWaste(Buffet currentBuffet) {
        return 0;
    }

    @Override
    public void refill(Buffet buffet) {
        for (Meal meal: buffet.meals()) {
            if(meal.timestamp().size()<2){
                meal.timestamp().add(0);
            }
        }
    }
    public List<Meal>fill(List<Meal>meals){
        meals.add(new Meal(MealType.BUN,timestamp()));
        meals.add(new Meal(MealType.CEREAL,timestamp()));
        meals.add(new Meal(MealType.CEREAL,timestamp()));
        meals.add(new Meal(MealType.CEREAL,timestamp()));
        meals.add(new Meal(MealType.CEREAL,timestamp()));
        return meals;
    }
    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public List<Integer> timestamp(){
        List<Integer>timestamp = new ArrayList<>(5);
        for (int i = 0; i < 5; i++){
            timestamp.add(i,0);
        }
        return timestamp;
    }

}
