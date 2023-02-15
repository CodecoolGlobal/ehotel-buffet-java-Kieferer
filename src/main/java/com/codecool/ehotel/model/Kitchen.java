package com.codecool.ehotel.model;

import java.util.List;

public record Kitchen(List<IngredientType> ingredients, List<IngredientType>consumedIngredients) {
}
