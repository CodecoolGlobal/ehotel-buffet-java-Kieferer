package com.codecool.ehotel.model;

public class Meal implements Comparable<Meal> {
    private final MealType mealType;
    public int timestamp;
    public Meal(MealType mealType, int timestamp) {
        this.mealType = mealType;
        this.timestamp = timestamp;
    }
    public MealType getMealType() {
        return mealType;
    }
    public int getTimestamp() {
        return timestamp;
    }
    public void tickTimestamp() {
        this.timestamp++;
    }
    @Override
    public int compareTo(Meal other) {
        if (this.timestamp >  other.timestamp) {
            return 1;
        }
        else if (this.timestamp < other.timestamp) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
