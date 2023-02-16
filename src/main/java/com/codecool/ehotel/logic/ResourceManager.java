package com.codecool.ehotel.logic;

import com.codecool.ehotel.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceManager {
    private static final ResourceManager instance = new ResourceManager();
    private ResourceManager(){}
    public static ResourceManager getInstance(){
        return instance;
    }
    private LocalDate simulationStartDate, simulationEndDate, simulationDate;
    private HashMap<MealType, Integer> mostConsumedMeals = new HashMap<>();
    public LocalDate getSimulationDate(){ return simulationDate; }
    public LocalDate getSimulationEndDate(){ return simulationEndDate; }
    public HashMap<MealType, Integer> getMostConsumedMeals(){
        return mostConsumedMeals;
    }
    public void incrementSpecificMeal(MealType mealType){
        if (mostConsumedMeals.containsKey(mealType))
            mostConsumedMeals.merge(mealType, 1, Integer::sum);
        else
            mostConsumedMeals.put(mealType, 1);
    }
    public void setSimulationInterval(LocalDate startDate, LocalDate endDate){
        simulationStartDate = startDate;
        simulationEndDate = endDate;
        simulationDate = simulationStartDate;
    }
    public Integer getLengthOfCycle() {
        return simulationEndDate.getDayOfMonth() - simulationStartDate.getDayOfMonth();
    }
    public void tickSimulationDate(){
        simulationDate = simulationDate.plusDays(1);
    }

    public Integer maxNumberOfGuests() {
        int averageGuestPerDay = 5;
        return averageGuestPerDay * getLengthOfCycle();
    }

}
