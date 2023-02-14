package com.codecool.ehotel;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.buffetRefill;
import com.codecool.ehotel.service.buffet.consumeFreshest;

import java.util.ArrayList;

public class EHotelBuffetApplication {

    public static void main(String[] args) {

        // Initialize services

        // Generate guests for the season

        // Run breakfast buffet
        Buffet buffet = new Buffet(new ArrayList<>());
        buffetRefill buffetRefill = new buffetRefill();
        consumeFreshest consumeFreshest = new consumeFreshest();
        buffetRefill.refill(buffet);
        consumeFreshest.consumeFreshest(buffet, MealType.BUN);
        buffet.meals().get(0).timestamp().add(0);;
        System.out.println(buffet.meals().get(0));
        for (int meal:buffet.meals().get(0).timestamp()) {
            System.out.println(meal);
        }
    }
}
