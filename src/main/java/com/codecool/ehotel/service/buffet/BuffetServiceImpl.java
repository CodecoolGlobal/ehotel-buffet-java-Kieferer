package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.*;

import java.util.*;

public class BuffetServiceImpl implements BuffetService {
    private Buffet buffet;
    public BuffetServiceImpl(Buffet buffet){
        this.buffet = buffet;
    }
    public int consumeFreshest(MealType preferredMeal) {
        orderByFreshness();
        boolean isFound = false;
        for (Meal meal : buffet.getMealList()) {
            if (meal.getMealType() == preferredMeal){
                isFound = true;
                buffet.removeFromMealList(meal);
                return 0;
            }
        }
        System.out.println("Preferred meal was " + preferredMeal + ", isFound: [" + isFound + "]");
        return 1;
    }
    public int dinnerConsumeFreshest(List<MealType> preferredMeal) {


        for (Meal meal : buffet.getMealList()) {
            for (int i = 0; i<preferredMeal.size(); i++){
                if (preferredMeal.get(i).getIngredients().equals( meal.getMealType().getIngredients())){
                    buffet.removeFromMealList(meal);
                    return 3-i;
                }
            }
            }
            return 0;

    }
    public void orderByFreshness(){
        Collections.sort(buffet.getMealList());
    }
    public void decreaseFreshness(Buffet buffet){
        for (Meal meal: buffet.getMealList()) {
            meal.tickTimestamp();
        }
    }
    @Override
    public int collectWaste() {
        int collectiveWastedMoney = 0;
        List<Meal> expiredMeal = new ArrayList<>();
        for (Meal currentMeal : buffet.getMealList()) {
            switch (currentMeal.getMealType().getDurability()) {
                case SHORT -> {
                    if (currentMeal.getTimestamp() > 3) {
                        collectiveWastedMoney += currentMeal.getMealType().getCost();
                        expiredMeal.add(currentMeal);

                    }
                }
                case MEDIUM -> {
                    if (currentMeal.getTimestamp() > 6) {
                        collectiveWastedMoney += currentMeal.getMealType().getCost();
                        expiredMeal.add(currentMeal);
                    }
                }
            }
        }
        buffet.removeAllFromMealList(expiredMeal);
        return collectiveWastedMoney;
    }
    @Override
    public void refill(List<Guest> guests, Buffet buffet) {
        HashMap<GuestType, Integer> amountOfSpecificGuests = new HashMap<>();
        HashMap<MealType, Integer> amountOfSpecificMeal = new HashMap<>();

        HashMap<MealType, Integer> amountOfDesiredMeal = new HashMap<>();
        // Iterate through guests and note how many specific type found
        for (Guest guest : guests){
            if (amountOfSpecificGuests.containsKey(guest.guestType())){
                amountOfSpecificGuests.merge(guest.guestType(), 1, Integer::sum);
            } else {
                amountOfSpecificGuests.put(guest.guestType(), 1);
            }
        }
        // Load every possible meals from MealTypes enum
        for (MealType meal : MealType.values()){
            if (meal.getCost() > 10000)
                amountOfSpecificMeal.put(meal, 0);
        }
        // Iterate through meal and note how many specific type found
        for (Meal meal : buffet.getMealList()){
            if (amountOfSpecificMeal.containsKey(meal.getMealType())) {
                amountOfSpecificMeal.merge(meal.getMealType(), 1, Integer::sum);
            }
        }
        // Calculate how many need from each specific meal
        for (GuestType guestType : GuestType.values()) {
            //Fill
            for (MealType mealType : guestType.getMealPreferences()) {
                if (amountOfDesiredMeal.containsKey(mealType)){
                    amountOfDesiredMeal.merge(mealType, 1, Integer::sum);
                } else {
                    amountOfDesiredMeal.put(mealType, 1);
                }
            }
            //Subtract found meals from desired meals
            for (MealType mealType : guestType.getMealPreferences()) {
                if (amountOfDesiredMeal.containsKey(mealType) && amountOfSpecificMeal.containsKey(mealType)){
                    amountOfDesiredMeal.merge(mealType, -amountOfSpecificMeal.get(mealType), Integer::sum);
                }
            }
        }
        for (MealType mealType : amountOfDesiredMeal.keySet()) {
            if (mealType.getCost() < 41)
                buffet.getMealList().add(new Meal(mealType, 0));
        }
    }
}
