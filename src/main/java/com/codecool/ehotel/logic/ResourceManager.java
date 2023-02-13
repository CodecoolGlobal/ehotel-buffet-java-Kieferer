package com.codecool.ehotel.logic;

import com.codecool.ehotel.model.Guest;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
    private static final ResourceManager instance = new ResourceManager();
    private ResourceManager(){}
    private static ResourceManager getInstance(){
        return instance;
    };
    List<Guest> guestList = new ArrayList<>();
}
