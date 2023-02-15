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
        for (Meal meal : buffet.getMealList()) {
            if (meal.equals(preferredMeal)){
                buffet.removeFromMealList(meal);
                return 0;
            }
        }
        return 1;
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
    public int collectWaste(Buffet buffet) {
        int collectiveWastedMoney = 0;
        List<Meal> expiredMeal = new ArrayList<>();
        for (Meal currentMeal : buffet.getMealList()) {
            System.out.println(currentMeal.getMealType());
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
        System.out.println("Guests in buffet: " + guests.size());
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
                if (amountOfDesiredMeal.containsKey(mealType)){
                    amountOfDesiredMeal.merge(mealType, -amountOfSpecificMeal.get(mealType), Integer::sum);
                }
            }
        }
        for (MealType mealType : amountOfDesiredMeal.keySet()) {
            buffet.getMealList().set(buffet.getMealList().size() - 1, new Meal(mealType, 0));
        }
    }
    public List<Meal> fill() {
        List<Meal> mealList = new ArrayList<>();
        mealList.add(new Meal(MealType.BUN, 0));
        mealList.add(new Meal(MealType.CEREAL, 0));
        mealList.add(new Meal(MealType.CROISSANT,0));
        mealList.add(new Meal(MealType.MILK,0));
        mealList.add(new Meal(MealType.FRIED_BACON,0));
        mealList.add(new Meal(MealType.MASHED_POTATO ,0));
        mealList.add(new Meal(MealType.MUFFIN, 0));
        mealList.add(new Meal(MealType.PANCAKE,0));
        mealList.add(new Meal(MealType.SCRAMBLED_EGGS, 0));
        mealList.add(new Meal(MealType.SUNNY_SIDE_UP, 0));
        return mealList;
    }
}
