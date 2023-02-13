package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.List;

public interface BuffetService {
    boolean consumeFreshest(Buffet currentBuffet, MealType meal);
    void collectWaste(Buffet currentBuffet);
    public Buffet refill(Buffet buffet);
}
