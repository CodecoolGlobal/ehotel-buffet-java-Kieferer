package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.*;

public class buffetRefill implements BuffetService{
    private Meal[]meals = new Meal[5];
    @Override
    public boolean consumeFreshest(Buffet currentBuffet, MealType meal) {
        return false;
    }

    @Override
    public Meal[] addNewPortion() {

        meals[0] = new Meal(MealType.BUN,timestamp());
        meals[1] = new Meal(MealType.CEREAL,timestamp());
        meals[2] = new Meal(MealType.CEREAL,timestamp());
        meals[3] = new Meal(MealType.CEREAL,timestamp());
        meals[4] = new Meal(MealType.CEREAL,timestamp());
        return meals;
    }
    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public int[] timestamp(){
        int[] timestamp = new int[getRandomNumberUsingNextInt(1,10)];
        for (int i = 0; i < timestamp.length; i++){
            timestamp[i] = getRandomNumberUsingNextInt(1,10);
        }
        return timestamp;
    }
}
