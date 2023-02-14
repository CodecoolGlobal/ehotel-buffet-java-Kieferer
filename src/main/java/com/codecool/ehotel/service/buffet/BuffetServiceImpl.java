package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;

public class BuffetServiceImpl implements BuffetService {


    public Buffet consumeFreshest(Buffet currentBuffet, MealType meal) {
        //placeholder variables.
        if (currentBuffet.meals().contains(meal)) {
            currentBuffet.meals().remove(meal);
            return currentBuffet;
        }
        return currentBuffet;
    }
    public int collectWaste(Buffet currentBuffet) {
return 0;
    }

    @Override
    public Buffet refill(Buffet buffet) {
        return null;
    }


}
