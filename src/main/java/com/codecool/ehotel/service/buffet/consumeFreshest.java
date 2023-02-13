package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

public class consumeFreshest implements BuffetService {

    public boolean consumeFreshest(Buffet currentBuffet, MealType meal) {
//        //placeholder variables.
//        if (currentBuffet.meals().containsKey(meal)) {
//            currentBuffet.meals().remove(meal);
//            return true;
//        }
        return false;
    }

    @Override
    public void collectWaste(Buffet currentBuffet) {

    }

    @Override
    public Buffet refill(Buffet buffet) {
        return null;
    }
}
