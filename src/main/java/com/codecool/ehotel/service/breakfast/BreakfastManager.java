package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Group;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;

import java.util.List;
import java.util.Random;

public class BreakfastManager {
    BuffetServiceImpl buffetService;
    private final Random random = new Random();
    public BreakfastManager(BuffetServiceImpl buffetService){
        this.buffetService = buffetService;
    }
    public void serve(List<Group> guestGroups, Buffet buffet){
        List<MealType> currentGuestPreference;
        int unhappyGuests = 0;
        int costOfWastedFood = 0;
        //Fill the buffet in the first day
        buffet.getMealList().addAll(buffetService.fill());
        for (int i = 0; i < guestGroups.size();i++) {
            buffetService.refill(guestGroups.get(i).guestGroup(), buffet);

            for (Guest guest : guestGroups.get(i).guestGroup()) {
                currentGuestPreference = guest.guestType().getMealPreferences();
                int currentPreference = random.nextInt(0, currentGuestPreference.size());
                unhappyGuests += buffetService.consumeFreshest(currentGuestPreference.get(currentPreference));
            }
            buffetService.decreaseFreshness(buffet);
            costOfWastedFood += buffetService.collectWaste();
        }
        // End cycle
        buffetService.decreaseFreshness(buffet);
        costOfWastedFood += buffetService.collectWaste();
        // Print out metrics
        System.out.println("During breakfast there were " + unhappyGuests + " unhappy guest.");
        System.out.println("After breakfast there were $" + costOfWastedFood + " of wasted food.");
    }

}
