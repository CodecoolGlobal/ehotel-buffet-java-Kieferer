package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.*;

public class buffetRefill implements BuffetService{
    @Override
    public Buffet consumeFreshest(Buffet currentBuffet, MealType meal) {
        return currentBuffet;
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
    public List<Integer> timestamp(){
        List<Integer>timestamp = new ArrayList<>(5);
        for (int i = 0; i < 5; i++){
            timestamp.add(i,0);
        }
        return timestamp;
    }

}
