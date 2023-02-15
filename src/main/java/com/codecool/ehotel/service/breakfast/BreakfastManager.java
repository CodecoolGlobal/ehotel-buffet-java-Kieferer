package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Group;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetRefill;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;

import java.util.List;
import java.util.Random;

public class BreakfastManager {
    private final Random random = new Random();
    BuffetRefill buffetRefill = new BuffetRefill();
    BuffetServiceImpl buffetService = new BuffetServiceImpl();
    public void serve(List<Group>guestGroups, Buffet buffet){
        List<MealType> currentGuestPreference;
        int unhappyGuests = 0;
        int costOfWastedFood = 0;

        for (int i =0; i <guestGroups.size();i++) {
            if (i < 1){
                buffet.meals().addAll(buffetRefill.fill());
            }
            buffetRefill.refill(buffet);

            for (Guest guest:guestGroups.get(i).guestGroup()) {
                currentGuestPreference= guest.guestType().getMealPreferences();
                int currentPreference = random .nextInt(0, currentGuestPreference.size());
                unhappyGuests += buffetService.consumeFreshest(buffet,currentGuestPreference.get(currentPreference));
            }
            buffetService.decreaseFreshness(buffet);
            costOfWastedFood += buffetService.collectWaste(buffet);
        }
        buffetService.decreaseFreshness(buffet);
        costOfWastedFood += buffetService.collectWaste(buffet);

    System.out.println("During breakfast there were " + unhappyGuests + " unhappy guest.");
    System.out.println("After breakfast there were $" + costOfWastedFood + " of wasted food.");

    }

}
