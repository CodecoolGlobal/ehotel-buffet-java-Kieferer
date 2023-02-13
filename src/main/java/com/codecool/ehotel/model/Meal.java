package com.codecool.ehotel.model;

import java.util.List;

public record Meal(MealType mealType, List<Integer> timestamp) {
}
