package com.codecool.ehotel.model;

import java.util.List;

public record Kitchen(List<Ingredient> ingredients, List<Ingredient>consumedIngredients) {
}
