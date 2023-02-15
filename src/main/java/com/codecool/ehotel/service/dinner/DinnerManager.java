package com.codecool.ehotel.service.dinner;

import com.codecool.ehotel.model.MealType;

import java.util.List;

public class DinnerManager {

    /* TODO: -> Menu with statistics how much menu items are preferred by guests.
                Individual preference list of the menu items generated upon the preference statistics.

                //Done but with no preferences

   */
    List<MealType> menu = List.of(MealType.CEREAL, MealType.MILK, MealType.PANCAKE, MealType.FRIED_SAUSAGE, MealType.BUN, MealType.CROISSANT, MealType.FRIED_BACON, MealType.MASHED_POTATO, MealType.SCRAMBLED_EGGS);

 /*

    TODO: -> Get the expected guests to appear(daily) as input-
            Guests have preference list - guests get happier if they can choose the top of their preferences from the menu
            The preferences must mirror the global statistics. Customer should tend to pick popular items higher in their lists.
            Customer happiness depend on the freshness of the components used for their meals.
            Popular items have higher probability to get higher positions

    TODO: -> The kitchen-
                        Stores ingredients needed for creating a meal,
                        Keep track of the consumed ingredients when preparing meal.
                        The ingredients must have date of expiry.

                        Strategy for buying ingredients-
                                    based on actual stock
                                    general preference statistics and expected future customer numbers

    TODO: -> Try to optimize greater happiness and lest waste

    TODO: -> Method for simulating one evening-
                                    get customers for that evening with their individual preference list
                                    serve their top preferred meals that can be prepared in order of guest appearance
                                    removing the required ingredients from the kitchen stock
                                    collect happiness numbers during the evening
                                    Sum up expired ingredients at the end of the day.
  */

}
