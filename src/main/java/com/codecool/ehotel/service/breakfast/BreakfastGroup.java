package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Group;
import com.codecool.ehotel.model.Guest;

import java.util.*;


public class BreakfastGroup{
    private final Random random = new Random();

    public List<Group> prepareBreakfastGroups(List<Guest> guests) {
        List<Guest> guestsToAssign = new LinkedList<>(guests);
        List<Group> groups = new LinkedList();

        for (double time = 6.0; time < 10; time += 0.5) {
            if(guestsToAssign.size() == 0) {
                break;
            }

            int currentGroupSize = random.nextInt(0, guestsToAssign.size());

            List<Guest> assignedGuests;
            if(time==9.5){
                assignedGuests = guestsToAssign;
            } else {
                assignedGuests = fillCurrentGroup(currentGroupSize, guestsToAssign);
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