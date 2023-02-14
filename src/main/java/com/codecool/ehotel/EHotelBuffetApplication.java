package com.codecool.ehotel;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.breakfast.BreakfastGroup;
import com.codecool.ehotel.service.buffet.buffetRefill;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        BuffetServiceImpl buffetServiceImpl = new BuffetServiceImpl();
        buffetRefill.refill(buffet);
        buffetServiceImpl.consumeFreshest(buffet, MealType.BUN);
        buffet.meals().get(0).timestamp().add(0);
        System.out.println(buffet.meals().get(0));
        for (int meal:buffet.meals().get(0).timestamp()) {
            System.out.println(meal);
        }
        BreakfastGroup breakfastGroup = new BreakfastGroup();
        Set<Guest> guests = new HashSet<>();
        //guestService.generateRandomGuest(LocalDate.parse("2018-12-01") , LocalDate.parse("2018-12-07"));
        guests.add(new Guest("Dan", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("Dasn", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("Ds3434ssan", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("Da434ssasn", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("D22asdasan", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("Daasdasdn", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("Dq34erweran", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("Dget56an", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("Da56u567n", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("D123fdsfan", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("Dxyxcycan", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("qeqweDan", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("2424Dan", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));
        guests.add(new Guest("qweqweDan", GuestType.BUSINESS, LocalDate.parse("2018-12-01"),LocalDate.parse("2018-12-07")));

        List<Group> groups = breakfastGroup.prepareBreakfastGroups(guests);
        System.out.println(groups.size());
        for (Group group:groups) {
            System.out.println(group);
        }
    }
}
