package com.codecool.ehotel.model;

import com.codecool.ehotel.logic.ResourceManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Guest {
    private final SortedSet<MealType> preferredMeals = new TreeSet<>();
    private final String name;
    private final GuestType guestType;
    private final LocalDate checkIn, checkOut;
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
    public GuestType getGuestType() {
        return guestType;
    }
    public LocalDate getCheckIn() {
        return checkIn;
    }
    public Integer getRemainingDays() {
        return checkOut.getDayOfMonth() - ResourceManager.getInstance().getSimulationDate().getDayOfMonth();
    }
}
