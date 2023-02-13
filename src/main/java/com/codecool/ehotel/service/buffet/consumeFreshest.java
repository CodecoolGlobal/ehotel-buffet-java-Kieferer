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
    /*    //placeholder variables.
        if (currentBuffet.meals().contains(meal)) {
            currentBuffet.meals().remove(meal);
            return true;
        }*/
        return false;
    }
}
