package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class BreakfastManager {
    BuffetServiceImpl buffetService;
    private final Random random = new Random();
    private HashMap<MealType, Integer> preferredMeal = new HashMap<>();

    public HashMap<MealType, Integer> preferredMeal() {
        return preferredMeal;
    }

    public BreakfastManager(BuffetServiceImpl buffetService) {
        this.buffetService = buffetService;
    }

    private int numOfDays = 0;

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
            //System.out.println("CYCLE " + (i + 1));
            for (Guest guest : guestGroups.get(i).guestGroup()) {
                currentGuestPreference = guest.guestType().getMealPreferences();
                int currentPreference = random.nextInt(0, currentGuestPreference.size());
                updatePreferredMeal(currentGuestPreference.get(currentPreference));
                unhappyGuests += buffetService.consumeFreshest(currentGuestPreference.get(currentPreference));
            }
            // End of cycles
            buffetService.decreaseFreshness(buffet);
            costOfWastedFood += buffetService.collectWaste();
        }
        getMostPreferredMeal();
        // End of day and print out metrics
        //System.out.println("During breakfast there were " + unhappyGuests + " unhappy guest from " + buffet.getGuestList().size() + ".");
        //System.out.println("After breakfast there were $" + costOfWastedFood + " of wasted food.");
        //System.out.println("Ratio: " + ((30 * unhappyGuests) + (costOfWastedFood / 10)) + "\n");
    }

    public void updatePreferredMeal(MealType meal) {
        if (preferredMeal.containsKey(meal)) {
            preferredMeal.merge(meal, 1, Integer::sum);
        } else {
            preferredMeal.put(meal, 1);
        }
    }

    public void getMostPreferredMeal() {
        preferredMeal.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, HashMap::new));
        for (Map.Entry entry : preferredMeal.entrySet()) {
            //System.out.println(entry.getValue());
            /*if(entry.getValue()){
            }*/
        }

    }
}