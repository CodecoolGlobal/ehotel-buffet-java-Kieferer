package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface GuestService {

    Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) throws FileNotFoundException;

    Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date);

}
