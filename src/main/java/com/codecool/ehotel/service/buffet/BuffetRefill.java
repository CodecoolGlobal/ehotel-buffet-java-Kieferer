
package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.*;

public class BuffetRefill implements BuffetService{
    @Override
    public int consumeFreshest(Buffet currentBuffet, MealType meal) {
        return 0;
    }

    @Override
    public int collectWaste(Buffet currentBuffet) {
        return 0;
    }

    @Override
    public void refill(Buffet buffet) {
             /*TODO: The refill takes the current guest group preferences
                and refills the buffet with the preferred meals if that have an amount less than X.
       */
        for (Meal meal: buffet.meals()) {
            if(meal.timestamp().size()<1){
                meal.timestamp().add(0);
            }
        }
    }
    public List<Meal> fill() {
        List<Meal> mealList = new ArrayList<>();
        mealList.add(new Meal(MealType.BUN,timestamp()));
        mealList.add(new Meal(MealType.CEREAL,timestamp()));
        mealList.add(new Meal(MealType.CROISSANT ,timestamp()));
        mealList.add(new Meal(MealType.MILK ,timestamp()));
        mealList.add(new Meal(MealType.FRIED_BACON ,timestamp()));
        mealList.add(new Meal(MealType.MASHED_POTATO ,timestamp()));
        mealList.add(new Meal(MealType.MUFFIN ,timestamp()));
        mealList.add(new Meal(MealType.PANCAKE ,timestamp()));
        mealList.add(new Meal(MealType.SCRAMBLED_EGGS ,timestamp()));
        mealList.add(new Meal(MealType.SUNNY_SIDE_UP ,timestamp()));
        return mealList;
    }
    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public List<Integer> timestamp(){
        List<Integer>timestamp = new ArrayList<>(8);
        for (int i = 0; i < 8; i++){
            timestamp.add(i,0);
        }
        return timestamp;
    }

}