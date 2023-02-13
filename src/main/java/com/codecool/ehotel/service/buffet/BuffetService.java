package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;

public interface BuffetService {
    boolean consumeFreshest(Buffet currentBuffet, MealType meal);
    void collectWaste(Buffet currentBuffet);
}
