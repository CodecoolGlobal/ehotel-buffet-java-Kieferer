package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealType;

import java.util.List;


public interface BuffetService {
    int consumeFreshest(MealType meal);

    int collectWaste();

    void refill(List<Guest> guests, Buffet buffet);
}
