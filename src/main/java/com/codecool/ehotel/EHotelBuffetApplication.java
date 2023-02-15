package com.codecool.ehotel;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.service.breakfast.BreakfastGroup;
import com.codecool.ehotel.service.breakfast.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetRefill;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EHotelBuffetApplication {
    //Hello

    public static void main(String[] args) throws FileNotFoundException {
        // Initialize services
        GuestService guestService = new GuestServiceImpl();
        BuffetService buffetService = new BuffetServiceImpl();
        BuffetRefill buffetManager = new BuffetRefill();
        Buffet buffet = new Buffet(new ArrayList<>());
        BreakfastGroup breakfastGroup = new BreakfastGroup();
        BreakfastManager breakfastManager = new BreakfastManager();

        ResourceManager.getInstance().setSimulationInterval(
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 10));
        // Generate guests for the season
        for (int i = 0; i < 100; i++) {
            ResourceManager.getInstance().addGuestToList(guestService.generateRandomGuest());
        }
        // Run breakfast buffet
        for (int i = 0; i < ResourceManager.getInstance().getLengthOfCycle(); i++){
            ResourceManager.getInstance().tickSimulationDate();

        }
        breakfastManager.serve(breakfastGroup.prepareBreakfastGroups(guestService.getGuestsForDay()),buffet);
        System.out.println("Wasted food: $" + buffetService.collectWaste(buffet));


    }
}
