package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;

public class consumeFreshest implements BuffetService {


    public boolean consumeFreshest(Buffet currentBuffet, MealType meal) {
        //placeholder variables.
        if (currentBuffet.meals().contains(meal)) {
            currentBuffet.meals().remove(meal.getDurability());
            return true;
        }
        return false;
    }
}
