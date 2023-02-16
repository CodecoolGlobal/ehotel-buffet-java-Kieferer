package com.codecool.ehotel.model;


import java.util.ArrayList;
import java.util.List;

public class Buffet {
    private final List<Guest> guestList = new ArrayList<>();
    private final List<Meal> mealList = new ArrayList<>();

    public List<Guest> getGuestList() {
        return guestList;
    }

    public List<Meal> getMealList() {
        return mealList;
    }

    public void addGuestToList(Guest guest) {
        guestList.add(guest);
    }

    public void addMealToList(Meal meal) {
        mealList.add(meal);
    }

    public void removeFromMealList(Meal meal) {
        mealList.remove(meal);
    }

    public void removeAllFromMealList(List<Meal> mealList) {
        this.mealList.removeAll(mealList);
    }
}
