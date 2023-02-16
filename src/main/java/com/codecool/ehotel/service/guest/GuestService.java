package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;

import java.io.FileNotFoundException;
import java.util.Set;

public interface GuestService {

    Guest generateRandomGuest() throws FileNotFoundException;

    Set<Guest> getGuestsForDay(Buffet buffet);

}
