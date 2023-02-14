package com.codecool.ehotel;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public class EHotelBuffetApplication {

    public static void main(String[] args) throws FileNotFoundException {

        // Initialize services
        GuestService guestService = new GuestServiceImpl();
        ResourceManager.getInstance().setSimulationInterval(
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 10));
        // Generate guests for the season
        for (int i = 0; i < 100; i++){
            ResourceManager.getInstance().addGuestToList(guestService.generateRandomGuest());
        }
        // Run breakfast buffet
        for (int i = 0; i < 10; i++){
            System.out.println("The simulationDate is ticked a day, current date is: " + ResourceManager.getInstance().getSimulationDate());
            ResourceManager.getInstance().tickSimulationDate();
            for (Guest guest : guestService.getGuestsForDay()){
                System.out.println("* - " + guest.name() + " guest has " + guest.getRemainingDays() + " remaining days.");
            }
        }

    }
}
