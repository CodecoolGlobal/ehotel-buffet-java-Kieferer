package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;


public interface BuffetService {
    int consumeFreshest(Buffet currentBuffet, MealType meal);
    int collectWaste(Buffet currentBuffet);
    void refill(Buffet buffet);
}
