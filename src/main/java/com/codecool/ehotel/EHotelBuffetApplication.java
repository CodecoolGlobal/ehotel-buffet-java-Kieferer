package com.codecool.ehotel;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.buffet.buffetRefill;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public class EHotelBuffetApplication {
    //Hello

    public static void main(String[] args) throws FileNotFoundException {
        // Initialize services
        GuestService guestService = new GuestServiceImpl();
        BuffetService buffetService = new BuffetServiceImpl();
        buffetRefill buffetManager = new buffetRefill();
        ResourceManager.getInstance().setSimulationInterval(
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 10));
        Buffet buffet = new Buffet(buffetManager.fill());
        // Generate guests for the season
        for (int i = 0; i < 100; i++) {
            ResourceManager.getInstance().addGuestToList(guestService.generateRandomGuest());
        }
        // Run breakfast buffet
        for (int i = 0; i < ResourceManager.getInstance().getLengthOfCycle(); i++){
            ResourceManager.getInstance().tickSimulationDate();

        }
        System.out.println("Wasted food: $" + buffetService.collectWaste(buffet));
    }
}
