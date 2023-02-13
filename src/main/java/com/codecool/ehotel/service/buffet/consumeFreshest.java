package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

public class consumeFreshest implements BuffetService {
    @Override
    public Meal[] addNewPortion() {
        return new Meal[0];
    }


    public boolean consumeFreshest(Buffet currentBuffet, MealType meal) {
       //placeholder variables.
        for (int i = 0; i < currentBuffet.preparedMeals().length; i++) {
            if(currentBuffet.preparedMeals()[i].mealType() == meal){
                currentBuffet.preparedMeals()[i].timestamp();
                
            }
        }
        return false;
    }
}
