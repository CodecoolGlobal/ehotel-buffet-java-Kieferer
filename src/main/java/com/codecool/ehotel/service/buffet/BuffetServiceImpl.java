package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.*;

import java.util.*;

public class BuffetServiceImpl implements BuffetService {
    private final Kitchen kitchen;
    private final Buffet buffet;
    public BuffetServiceImpl(Buffet buffet, Kitchen kitchen){
        this.buffet = buffet;
        this.kitchen = kitchen;
    }

    public int createMeal(MealType meal) {
        int fressnessOfIngredients = 0;
        //Ingredients sorted from freshest to oldest for better
        orderByFreshness(kitchen.getAvailableIngredients());
        //Check for ingredients and use them if found and return happiness points by factors
        for (IngredientType ingredient : meal.getIngredients()) {
            fressnessOfIngredients += ingredient.getFreshness();
            if(!kitchen.getAvailableIngredients().contains(ingredient)){
                //When we can't make a meal because we are low on ingredients,
                //its gives as minus 10 happiness points.
                return -10;
            }
        }
        //Update kitchens database
        kitchen.addAllToConsumedIngredients(meal.getIngredients());
        kitchen.removeAllFromAvailableIngredients(meal.getIngredients());
        //Return freshness to subtract it from happiness points.
        return fressnessOfIngredients;
    }

    public int consumeFreshest(MealType preferredMeal) {
        orderByFreshness(buffet.getMealList());
        boolean isFound = false;
        for (Meal meal : buffet.getMealList()) {
            if (meal.getMealType() == preferredMeal){
                ResourceManager.getInstance().incrementSpecificMeal(meal.getMealType());
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
            for (MealType preferredMealType : preferredMeal) {
                if (meal.getMealType() == preferredMealType) {
                    buffet.removeFromMealList(meal);
                    return (preferredMeal.size() - preferredMeal.indexOf(preferredMealType)) - createMeal(meal.getMealType());
                }
            }
        }
        return 0;
    }
    public void orderByFreshness(List list){
        Collections.sort(list);
    }
    public void decreaseIngredientFreshness() {
        for (IngredientType ingredient : kitchen.getAvailableIngredients()) {
            ingredient.setFreshness();
        }
    }
    public void decreaseFreshness(){
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


    public int collectExpiredIngredients() {
        int wastedMoneyOnIngredients = 0;
        List<IngredientType> expiredIngredient = new ArrayList<>();
        for (IngredientType currentIngredient : kitchen.getAvailableIngredients()) {
            switch (currentIngredient.getDurability()) {
                case SHORT -> {
                    if (currentIngredient.getFreshness() > 3) {
                        wastedMoneyOnIngredients += currentIngredient.getCost();
                        expiredIngredient.add(currentIngredient);
                    }
                }
                case MEDIUM -> {
                    if (currentIngredient.getFreshness() > 6) {
                        wastedMoneyOnIngredients += currentIngredient.getCost();
                        expiredIngredient.add(currentIngredient);
                    }
                }
            }
        }
        kitchen.addAllToConsumedIngredients(expiredIngredient);
        kitchen.removeAllFromAvailableIngredients(expiredIngredient);
        return wastedMoneyOnIngredients;
    }
    public void calculateExpenditureUponBuyingIngredients(MealType meal){
        int expenditure = ResourceManager.getInstance().getExpendituresOnIngredients();
        for (IngredientType ingredient: meal.getIngredients()) {
            expenditure += ingredient.getCost();
        }
        ResourceManager.getInstance().increaseExpendituresOnIngredients(expenditure);
    }

    public void refillKitchenIngredients(Set<Guest> guestsOfTheDay, Kitchen kitchen) {
        for (Guest guests : guestsOfTheDay) {
            for (MealType meal : guests.getPreferredMeals()) {
                kitchen.refillIngredients(meal.getIngredients());
                calculateExpenditureUponBuyingIngredients(meal);
            }
        }
    }
    @Override
    public void refill(List<Guest> guestList, Buffet buffet) {
        HashMap<GuestType, Integer> amountOfSpecificGuests = new HashMap<>();
        HashMap<MealType, Integer> amountOfSpecificMeal = new HashMap<>();

        HashMap<MealType, Integer> amountOfDesiredMeal = new HashMap<>();
        // Iterate through guests and note how many specific type found
        for (Guest guests : guestList){
            if (amountOfSpecificGuests.containsKey(guests.getGuestType())){
                amountOfSpecificGuests.merge(guests.getGuestType(), 1, Integer::sum);
            } else {
                amountOfSpecificGuests.put(guests.getGuestType(), 1);
            }
        }
        // Load every possible meals from MealTypes enum
        for (MealType meal : MealType.values()){
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
