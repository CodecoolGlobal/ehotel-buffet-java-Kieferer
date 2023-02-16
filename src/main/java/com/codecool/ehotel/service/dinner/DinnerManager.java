package com.codecool.ehotel.service.dinner;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.Kitchen;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.util.List;
import java.util.Set;

public class DinnerManager {

    private BuffetServiceImpl buffetService;
    private int numOfDays = 0;
    private int happinessRatio = 0;

    public DinnerManager(BuffetServiceImpl buffetService) {
        this.buffetService = buffetService;
    }
    /* TODO: -> Menu with statistics how much menu items are preferred by guests.
                Individual preference list of the menu items generated upon the preference statistics.

                //Done but with no preferences

   */
    List<MealType> menu = List.of(MealType.CEREAL, MealType.MILK, MealType.PANCAKE, MealType.FRIED_SAUSAGE, MealType.BUN, MealType.CROISSANT, MealType.FRIED_BACON, MealType.MASHED_POTATO, MealType.SCRAMBLED_EGGS);
    public  void serve(Set<Guest> guest, Buffet buffet){
        //These codes run daily
        numOfDays++;
        System.out.println("DAY " + numOfDays);
        List<MealType> currentGuestPreference;
        int unhappyGuests = 0;
        //Codes in the body of the for-loop runs 8 times a day as cycles
        for (Guest currentguest : guest) {
            //buffetService.refill(guestGroups.get(i).guestGroup(), buffet);
            //System.out.println("CYCLE " + (i + 1));
                currentGuestPreference = currentguest.getGuestType().getMealPreferences();
                int currentHappiness = buffetService.dinnerConsumeFreshest(currentGuestPreference);
                if (currentHappiness == 0)
                    unhappyGuests++;
                else
                    happinessRatio+=currentHappiness;

            }
            // End of cycles
            buffetService.decreaseFreshness(buffet);

        System.out.println("During dinner there were " + unhappyGuests + " unhappy guest from " + guest.size() + ".");
        System.out.println("Ratio: "+ happinessRatio);
        }
        // End of day and print out metrics
        //System.out.println("After breakfast there were $" + costOfWastedFood + " of wasted food.");
    }

 /*

    TODO: -> Get the expected guests to appear(daily) as input-
            Guests have preference list - guests get happier if they can choose the top of their preferences from the menu
            The preferences must mirror the global statistics. Customer should tend to pick popular items higher in their lists.
            Customer happiness depend on the freshness of the components used for their meals.
            Popular items have higher probability to get higher positions

    TODO: -> The kitchen-
                        Strategy for buying ingredients-
                                    based on actual stock
                                    general preference statistics and expected future customer numbers

    TODO: -> Method for simulating one evening-
                                    get customers for that evening with their individual preference list
                                    serve their top preferred meals that can be prepared in order of guest appearance
                                    removing the required ingredients from the kitchen stock
                                    collect happiness numbers during the evening
                                    Sum up expired ingredients at the end of the day.
  */


