package com.codecool.ehotel.model;

import com.codecool.ehotel.logic.ResourceManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Guest {
    private final SortedSet<MealType> preferredMeals = new TreeSet<>();
    private String name;
    private GuestType guestType;
    private LocalDate checkIn, checkOut;

    public Guest(String name, GuestType guestType, LocalDate checkIn, LocalDate checkOut) {
        this.name = name;
        this.guestType = guestType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public SortedSet<MealType> getPreferredMeals() {
        return preferredMeals;
    }

    public void sortPreferredMeals() {
        HashMap<MealType, Integer> meals = ResourceManager.getInstance().getMostConsumedMeals();
        var mealList = new ArrayList<>(meals.entrySet());
        mealList.sort((currMeal, mealAfterCurrMeal) -> mealAfterCurrMeal.getValue() - currMeal.getValue());
        for (var meal : mealList) {
            if (guestType.getMealPreferences().contains(meal.getKey()))
                preferredMeals.add(meal.getKey());
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


    public Integer getRemainingDays() {
        return checkOut.getDayOfMonth() - ResourceManager.getInstance().getSimulationDate().getDayOfMonth();
    }
}
