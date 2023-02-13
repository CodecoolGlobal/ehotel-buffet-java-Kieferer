package com.codecool.ehotel.utils;

public class Random {
    public static Integer Range(int min, int max){
        return (int)(Math.random() * (max - min) + min);
    }
}
