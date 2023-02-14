package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Group;
import com.codecool.ehotel.model.Guest;

import java.util.*;


public class BreakfastGroup{
    private Random random = new Random();

    public List<Group> prepareBreakfastGroups(Set<Guest> guests) {
        List<Guest> guestsToAssing = new LinkedList<>(guests);
        List<Group> groups = new LinkedList();

        for (double time = 6.0; time < 10; time += 0.5) {
            if(guestsToAssing.size() == 0) {
                break;
            }

            int currentGroupSize = random.nextInt(0, guestsToAssing.size());

            List<Guest> assignedGuests;
            if(time==9.5){
                assignedGuests = guestsToAssing;
            } else {
                assignedGuests = fillCurrentGroup(currentGroupSize, guestsToAssing);
            }
            groups.add(new Group(assignedGuests));
        }

        return groups;
    }

    public List<Guest> fillCurrentGroup(int currentGroupSize, List<Guest> guestsToAssing) {
        List<Guest> guestsToSplitIntoGroups = new ArrayList<>(currentGroupSize);

        for (int i = 0; i < currentGroupSize; i++) {
            int randomNumber = random.nextInt(0, guestsToAssing.size());
            Guest guest = guestsToAssing.remove(randomNumber);
            guestsToSplitIntoGroups.add(guest);
        }
        System.out.println("Left to appear" + guestsToAssing.size());

        return guestsToSplitIntoGroups;
    }
}