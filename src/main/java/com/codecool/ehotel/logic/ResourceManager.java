package com.codecool.ehotel.logic;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
    private static final ResourceManager instance = new ResourceManager();
    private ResourceManager(){}
    public static ResourceManager getInstance(){
        return instance;
    }
    private LocalDate simulationStartDate, simulationEndDate, simulationDate;
    private final List<Guest> guestList = new ArrayList<>();
    public List<Guest> getGuestList() {
        return guestList;
    }
    private final Buffet buffet = new Buffet(new ArrayList<>());
    public Buffet getBuffet() {
        return buffet;
    }

    public LocalDate getSimulationDate(){ return simulationDate; }
    public LocalDate getSimulationEndDate(){ return simulationEndDate; }
    public void setSimulationInterval(LocalDate startDate, LocalDate endDate){
        simulationStartDate = startDate;
        simulationEndDate = endDate;
        simulationDate = simulationStartDate;
    }
    public Integer getLengthOfCycle() {
        return simulationEndDate.getDayOfMonth() - simulationStartDate.getDayOfMonth();
    }
    public void tickSimulationDate(){
        simulationDate = simulationDate.plusDays(1);
    }
    public void addGuestToList(Guest guest){
        guestList.add(guest);
    }

}
