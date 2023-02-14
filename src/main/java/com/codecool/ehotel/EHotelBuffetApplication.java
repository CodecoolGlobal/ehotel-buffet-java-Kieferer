package com.codecool.ehotel;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.buffetRefill;
import com.codecool.ehotel.service.buffet.consumeFreshest;

import java.util.ArrayList;

public class EHotelBuffetApplication {

    public static void main(String[] args) throws FileNotFoundException {

        // Initialize services
        GuestService guestService = new GuestServiceImpl();
        // Generate guests for the season
        for (int i = 0; i < 100; i++){
            ResourceManager.getInstance().addGuestToList(guestService.generateRandomGuest(LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 10)));
        }
        // Run breakfast buffet
        for (Guest guest : ResourceManager.getInstance().getGuestList()) {
            System.out.println(guest.name());
        }

        Buffet buffet = new Buffet(new ArrayList<>());
        buffetRefill buffetRefill = new buffetRefill();
        consumeFreshest consumeFreshest = new consumeFreshest();
        buffetRefill.refill(buffet);
        consumeFreshest.consumeFreshest(buffet, MealType.BUN);
    }
}
