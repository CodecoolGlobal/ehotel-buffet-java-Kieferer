package com.codecool.ehotel;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.breakfast.BreakfastGroup;
import com.codecool.ehotel.service.buffet.buffetRefill;
import com.codecool.ehotel.service.buffet.consumeFreshest;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EHotelBuffetApplication {

    public static void main(String[] args) throws FileNotFoundException {

        // Initialize services

        // Generate guests for the season

        // Run breakfast buffet
        Buffet buffet = new Buffet(new ArrayList<>());
        buffetRefill buffetRefill = new buffetRefill();
        consumeFreshest consumeFreshest = new consumeFreshest();
        buffetRefill.refill(buffet);
        consumeFreshest.consumeFreshest(buffet, MealType.BUN);
        BreakfastGroup breakfastGroup = new BreakfastGroup();
        Set<Guest>guests = new HashSet<>();
        GuestServiceImpl guestService = new GuestServiceImpl();
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

        List<Group>groups = breakfastGroup.prepareBreakfastGroups(guests);
        System.out.println(groups.size());
        for (Group group:groups) {
            System.out.println(group);
        }
    }
}
