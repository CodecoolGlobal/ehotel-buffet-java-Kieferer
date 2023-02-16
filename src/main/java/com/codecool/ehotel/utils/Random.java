package com.codecool.ehotel.utils;

import java.time.LocalDate;

public class Random {
    public static Integer Range(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
    public static LocalDate RangeDate(LocalDate startDate, LocalDate endDate) {
        return LocalDate.of(
                Range(startDate.getYear(), endDate.getYear()),
                Range(startDate.getMonthValue(), endDate.getMonthValue()),
                Range(startDate.getDayOfMonth(), endDate.getDayOfMonth()));
    }
}
