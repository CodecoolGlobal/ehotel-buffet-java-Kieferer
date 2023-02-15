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
        Buffet buffet = new Buffet(new ArrayList<>());
        BreakfastGroup breakfastGroup = new BreakfastGroup();
        BreakfastManager breakfastManager = new BreakfastManager();
        ResourceManager globalResource = ResourceManager.getInstance();
        // Set length of season
        int lengthOfSeason = 10;
        globalResource.setSimulationInterval(
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 1).plusDays(lengthOfSeason));
        // Generate guests for the season
        for (int i = 0; i < globalResource.maxNumberOfGuests(); i++) {
            globalResource.addGuestToList(guestService.generateRandomGuest());
        }
        // Run breakfast buffet
        for (int i = 0; i < globalResource.getLengthOfCycle(); i++) {
            // Serve breakfast and print out metrics
            breakfastManager.serve(breakfastGroup.prepareBreakfastGroups(guestService.getGuestsForDay()), buffet);
            globalResource.tickSimulationDate();
        }
    }
}
