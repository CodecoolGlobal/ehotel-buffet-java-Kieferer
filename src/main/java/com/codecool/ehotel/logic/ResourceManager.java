package com.codecool.ehotel.logic;

import com.codecool.ehotel.model.Guest;
import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
    private static final ResourceManager instance = new ResourceManager();
    private ResourceManager(){}
    public static ResourceManager getInstance(){
        return instance;
    }
    private List<Guest> guestList = new ArrayList<>();
    public List<Guest> getGuestList() {
        return guestList;
    }
    public void addGuestToList(Guest guest){
        guestList.add(guest);
    }
}
