package com.codecool.ehotel.utils;

public class TimeParser {
    public static String getTime(Integer hour, Integer cycle) {
        int hours = (hour + (cycle * 30) / 60);
        String minutes = (cycle % 2 == 0 ? "00" : "30");
        return (Integer.toString(hours).length() > 1 ? hours : "0" + hours) + ":" + minutes;
    }
}
