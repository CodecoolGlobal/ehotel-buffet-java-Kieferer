package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;


public interface BuffetService {
    public Buffet consumeFreshest(Buffet currentBuffet, MealType meal);
    void collectWaste(Buffet currentBuffet);
    public Buffet refill(Buffet buffet);
}
