package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.utils.JSONReader;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class GuestServiceImpl implements GuestService {

    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) throws FileNotFoundException {
        //return new Guest(JSONReader.getRandomFromJSONFile(), GuestType.);
        return null;
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        return null;
    }
}
