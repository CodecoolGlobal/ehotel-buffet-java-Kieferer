package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.logic.ResourceManager;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.utils.JSONReader;
import com.codecool.ehotel.utils.Random;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GuestServiceImpl implements GuestService {

    @Override
    public Guest generateRandomGuest() throws FileNotFoundException {
        //Reservation startDate is can't be before the current simulation date.
        //So seasonStart is the all-time current simulation date.
        LocalDate seasonStart = ResourceManager.getInstance().getSimulationDate();
        LocalDate seasonEnd = ResourceManager.getInstance().getSimulationEndDate();
        //Create a startDate and endDate (limit) which is inside the interval of season.
        LocalDate startDate = Random.RangeDate(seasonStart, seasonEnd);
        int limit = seasonEnd.getDayOfMonth() - seasonStart.getDayOfMonth();
        return new Guest(JSONReader.getRandomFromJSONFile(),
                GuestType.valueOf(GuestType.values()[Random.Range(0, GuestType.values().length - 1)].name()),
                startDate,
                LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth() + Random.Range(1, limit))
        );
    }

    @Override
    public Set<Guest> getGuestsForDay() {
        int simulatedDayOfMonth = ResourceManager.getInstance().getSimulationDate().getDayOfMonth();
        //We should check and remove a guest from the list, if the current simulation date is further than its checkout date.
        ResourceManager.getInstance().getGuestList().removeIf(guest ->
                (guest.getRemainingDays() < 0));
        //Now we have the whole list of currently available guests, but have to filter to those whose reservation starting date
        //is the same as current date, or we are already beyond.
        System.out.println("available: " + ResourceManager.getInstance().getGuestList().size());
        return ResourceManager.getInstance().getGuestList().stream().filter(guest ->
                guest.checkIn().getDayOfMonth() <= simulatedDayOfMonth).collect(Collectors.toSet());
    }
}
