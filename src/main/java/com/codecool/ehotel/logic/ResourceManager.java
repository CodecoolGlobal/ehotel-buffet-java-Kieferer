package com.codecool.ehotel.logic;

import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.util.HashMap;

public class ResourceManager {
    private static final ResourceManager instance = new ResourceManager();
    private final HashMap<MealType, Integer> mostConsumedMeals = new HashMap<>();
    public int expendituresOnIngredients = 0;
    private LocalDate simulationStartDate, simulationEndDate, simulationDate;
    private ResourceManager() {
    }

    public static ResourceManager getInstance() {
        return instance;
    }

    public LocalDate getSimulationDate() {
        return simulationDate;
    }

    public LocalDate getSimulationEndDate() {
        return simulationEndDate;
    }

    public HashMap<MealType, Integer> getMostConsumedMeals() {
        return mostConsumedMeals;
    }

    public void incrementSpecificMeal(MealType mealType) {
        if (mostConsumedMeals.containsKey(mealType))
            mostConsumedMeals.merge(mealType, 1, Integer::sum);
        else
            mostConsumedMeals.put(mealType, 1);
    }

    public void setSimulationInterval(LocalDate startDate, LocalDate endDate) {
        simulationStartDate = startDate;
        simulationEndDate = endDate;
        simulationDate = simulationStartDate;
    }

    public Integer getLengthOfCycle() {
        return simulationEndDate.getDayOfMonth() - simulationStartDate.getDayOfMonth();
    }

    public void tickSimulationDate() {
        simulationDate = simulationDate.plusDays(1);
    }

    public Integer maxNumberOfGuests() {
        int averageGuestPerDay = 5;
        return averageGuestPerDay * getLengthOfCycle();
    }

    public int getExpendituresOnIngredients() {
        return expendituresOnIngredients;
    }

    public void increaseExpendituresOnIngredients(int cost) {
        expendituresOnIngredients += cost;
    }
}
