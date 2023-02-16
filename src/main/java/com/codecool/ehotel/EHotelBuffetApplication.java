package com.codecool.ehotel;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.breakfast.BreakfastGroup;
import com.codecool.ehotel.service.breakfast.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.dinner.DinnerManager;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class EHotelBuffetApplication {
    //Hello

    public static void main(String[] args) throws FileNotFoundException {
        // Initialize services
        Buffet buffet = new Buffet();
        GuestService guestService = new GuestServiceImpl();
        BuffetServiceImpl buffetService = new BuffetServiceImpl(buffet);
        BreakfastGroup breakfastGroup = new BreakfastGroup();
        BreakfastManager breakfastManager = new BreakfastManager(buffetService);
        DinnerManager dinnerManager = new DinnerManager(buffetService);
        ResourceManager globalResource = ResourceManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        int lengthOfSeason = 0;
        // Set length of season
        while (lengthOfSeason <= 0){
            System.out.println("How many days do you want to simulate?");
            lengthOfSeason = scanner.nextInt();
        }
        globalResource.setSimulationInterval(
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 1).plusDays(lengthOfSeason));
        // Generate guests for the season
        for (int i = 0; i < globalResource.maxNumberOfGuests(); i++) {
            buffet.addGuestToList(guestService.generateRandomGuest());
        }
        // Run breakfast buffet
        for (int i = 0; i < globalResource.getLengthOfCycle(); i++) {
            // Serve breakfast and print out metrics
            breakfastManager.serve(breakfastGroup.prepareBreakfastGroups(guestService.getGuestsForDay(buffet)), buffet);
            dinnerManager.serve(guestService.getGuestsForDay(buffet), buffet);
            globalResource.tickSimulationDate();
        }
    }
}
