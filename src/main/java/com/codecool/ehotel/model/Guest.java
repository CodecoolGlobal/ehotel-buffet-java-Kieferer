package com.codecool.ehotel.model;

import com.codecool.ehotel.logic.ResourceManager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Guest {
    private String name;
    private GuestType guestType;
    private LocalDate checkIn, checkOut;
    private SortedSet preferredMeals = new TreeSet();

    public SortedSet getPreferredMeals() {
        return preferredMeals;
    }
    public Guest(String name, GuestType guestType, LocalDate checkIn, LocalDate checkOut){
        this.name = name;
        this.guestType = guestType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public void sortPreferredMeals() {
        /*

    Get all entries by calling entrySet() method of Map
    Create a custom Comparator to sort entries based upon values
    Convert entry set to list
    Sort entry list by using Collections.sort() method by passing your value comparator
    Create a LinkedHashMap by adding entries in sorted order.

         */

        SortedSet list = new TreeSet();
        for (var key : ResourceManager.getInstance().getMostConsumedMeals().keySet()){

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GuestType getGuestType() {
        return guestType;
    }

    public void setGuestType(GuestType guestType) {
        this.guestType = guestType;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }


    public Integer getRemainingDays(){
        return checkOut.getDayOfMonth() - ResourceManager.getInstance().getSimulationDate().getDayOfMonth();
    }
}
