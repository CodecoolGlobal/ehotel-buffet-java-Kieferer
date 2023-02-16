package com.codecool.ehotel.service.serving.breakfast;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Group;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.utils.TimeParser;

import java.util.List;
import java.util.Random;

public class BreakfastManager {
    private final Random random = new Random();
    BuffetServiceImpl buffetService;
    private int numOfDays = 0;

    public BreakfastManager(BuffetServiceImpl buffetService) {
        this.buffetService = buffetService;
    }

    public void serve(List<Group> guestGroups, Buffet buffet) {
        //These codes run daily
        numOfDays++;
        System.out.println("DAY " + numOfDays);
        List<MealType> currentGuestPreference;
        int unhappyGuests = 0;
        int costOfWastedFood = 0;
        //Codes in the body of the for-loop runs 8 times a day as cycles
        for (int i = 0; i < guestGroups.size(); i++) {
            buffetService.refill(guestGroups.get(i).guestGroup(), buffet);
            System.out.println("CYCLE " + (i + 1) + " at " + TimeParser.getTime(6, i + 1));
            for (Guest guest : guestGroups.get(i).guestGroup()) {
                currentGuestPreference = guest.getGuestType().getMealPreferences();
                int currentPreference = random.nextInt(0, currentGuestPreference.size());
                unhappyGuests += buffetService.consumeFreshest(currentGuestPreference.get(currentPreference));
            }
            // End of cycles
            buffetService.decreaseFreshness(buffet);
            costOfWastedFood += buffetService.collectWaste();
        }
        // End of day and print out metrics
        System.out.println("During breakfast there were " + unhappyGuests + " unhappy guest from " + buffet.getGuestList().size() + ".");
        System.out.println("After breakfast there were $" + costOfWastedFood + " of wasted food.");
        System.out.println("Ratio: " + ((30 * unhappyGuests) + (costOfWastedFood / 10)) + "\n");
    }
}