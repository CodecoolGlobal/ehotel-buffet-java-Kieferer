package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.*;

public class buffetRefill implements BuffetService{
    @Override
    public boolean consumeFreshest(Buffet currentBuffet, MealType meal) {
        return false;
    }

    @Override
    public void collectWaste(Buffet currentBuffet) {

    }

    @Override
    public Buffet refill(Buffet buffet) {
        buffet.meals().add(new Meal(MealType.BUN,timestamp()));
        buffet.meals().add(new Meal(MealType.CEREAL,timestamp()));
        buffet.meals().add(new Meal(MealType.CEREAL,timestamp()));
        buffet.meals().add(new Meal(MealType.CEREAL,timestamp()));
        buffet.meals().add(new Meal(MealType.CEREAL,timestamp()));
        return buffet;
    }
    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public int[] timestamp(){
        int[] timestamp = new int[getRandomNumberUsingNextInt(1,10)];
        for (int i = 0; i < timestamp.length; i++){
            timestamp[i] = 0;
        }
        return timestamp;
    }
}
