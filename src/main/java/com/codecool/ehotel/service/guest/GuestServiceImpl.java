package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.utils.JSONReader;
import com.codecool.ehotel.utils.Random;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class GuestServiceImpl implements GuestService {

    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) throws FileNotFoundException {
        LocalDate startDate = Random.RangeDate(seasonStart, seasonEnd);
        int limit = seasonEnd.getDayOfMonth() - seasonStart.getDayOfMonth();
        return new Guest(JSONReader.getRandomFromJSONFile(),
                GuestType.valueOf(GuestType.values()[Random.Range(0, GuestType.values().length - 1)].name()),
                startDate,
                LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth() + Random.Range(1, limit))
        );
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        return null;
    }
}
