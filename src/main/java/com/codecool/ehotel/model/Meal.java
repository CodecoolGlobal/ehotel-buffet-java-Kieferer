package com.codecool.ehotel.model;

public class Meal implements Comparable {
    private MealType mealType;
    private int timestamp;

    public Meal(MealType mealType, int timestamp) {
        this.mealType = mealType;
        this.timestamp = timestamp;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void tickTimestamp() {
        this.timestamp++;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
