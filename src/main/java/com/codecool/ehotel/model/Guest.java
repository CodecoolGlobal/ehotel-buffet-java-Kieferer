package com.codecool.ehotel.model;

import com.codecool.ehotel.logic.ResourceManager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Guest(String name, GuestType guestType, LocalDate checkIn, LocalDate checkOut) {
    public Integer getRemainingDays(){
        return checkOut.getDayOfMonth() - ResourceManager.getInstance().getSimulationDate().getDayOfMonth();
    }
    public void sortPreference(){

    }
}
