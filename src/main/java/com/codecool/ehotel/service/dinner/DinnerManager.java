package com.codecool.ehotel.service.dinner;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.Kitchen;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import java.util.Set;

public class DinnerManager {
    private final BuffetServiceImpl buffetService;
    private final Kitchen kitchen;
    public DinnerManager(BuffetServiceImpl buffetService, Kitchen kitchen) {
        this.buffetService = buffetService;
        this.kitchen = kitchen;
    }
    public  void serve(Set<Guest> guests){
        int waste = 0;
        int happinessRatio = 0;
        buffetService.refillKitchenIngredients(guests,kitchen);
        for (Guest guest : guests) {
            guest.sortPreferredMeals();
            happinessRatio += buffetService.dinnerConsumeFreshest(guest.getPreferredMeals().stream().toList());
        }
        buffetService.decreaseFreshness();
        waste += buffetService.collectExpiredIngredients();
        // End of day and print out metrics
        System.out.println("Collected happiness points during dinner: "+ happinessRatio);
        System.out.println("After dinner there were $" + waste + " of wasted food.\n");
        //Tick freshness timestamps of ingredients
        buffetService.decreaseIngredientFreshness();
    }
}


