package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Group;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.buffet.BuffetRefill;

import java.util.List;
import java.util.Random;

public class BreakfastManager {
    private Random random = new Random();
    BuffetRefill buffetRefill = new BuffetRefill();
    public void serve(List<Group>guestGroups, Buffet buffet){
    int unhappyGuests = 0;
    int costOfWastedFood = 0;
    for (int i =0; i <guestGroups.size();i++) {
        if (i == 0){
            buffetRefill.fill(buffet.meals());
            for (Guest guest:guestGroups.get(i).guestGroup()) {
                int currentPreference = random .nextInt(0, guest.guestType().getMealPreferences().size());
                buffetRefill.consumeFreshest(buffet,guest.guestType().getMealPreferences().get(currentPreference));
            }
        } else {
            buffetRefill.refill(buffet);
            for (Guest guest:guestGroups.get(i).guestGroup()) {
                int currentPreference = random .nextInt(0, guest.guestType().getMealPreferences().size());
                buffetRefill.consumeFreshest(buffet,guest.guestType().getMealPreferences().get(currentPreference));
            }
        }
    }
        /*TODO:Each cycle : fill or refill(any type any number) buffet/ consume breakfast->
           guest search mealPreferences 0 at first if empty the guest becomes unhappy
           discard old meals that are not fresh
           at the end of the cycles all meal discarded except the long ones.
           collect and display the number of unhappy guests and cost of waste     */

    }

}
