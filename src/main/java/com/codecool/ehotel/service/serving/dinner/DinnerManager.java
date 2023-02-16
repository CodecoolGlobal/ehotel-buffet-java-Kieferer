package com.codecool.ehotel.service.serving.dinner;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.kitchen.KitchenManager;

import java.util.Set;

public class DinnerManager {
    private final BuffetServiceImpl buffetService;
    private final KitchenManager kitchenManager;

    public DinnerManager(BuffetServiceImpl buffetService, KitchenManager kitchenManager) {
        this.buffetService = buffetService;
        this.kitchenManager = kitchenManager;
    }

    public void serve(Set<Guest> guests, Buffet buffet) {
        int waste = 0;
        int happinessRatio = 0;
        kitchenManager.refillKitchenIngredients(guests);
        for (Guest guest : guests) {
            guest.sortPreferredMeals();
            happinessRatio += buffetService.dinnerConsumeFreshest(guest.getPreferredMeals().stream().toList(), kitchenManager);
        }
        buffetService.decreaseFreshness(buffet);
        waste += kitchenManager.collectExpiredIngredients();
        // End of day and print out metrics
        System.out.println("Collected happiness points during dinner: " + happinessRatio);
        System.out.println("After dinner there were $" + waste + " of wasted food.\n");
        //Tick freshness timestamps of ingredients
        kitchenManager.decreaseFreshness();
    }
}


