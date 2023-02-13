package com.codecool.ehotel.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONReader {
    public static String getRandomFromJSONFile() throws FileNotFoundException {
        String path = "src/main/java/com/codecool/ehotel/data/names.json";
        JsonArray stringArray = (JsonArray) JsonParser.parseReader(new FileReader(path));
        return stringArray.get(Random.Range(0, stringArray.size() - 1)).getAsString();
    }
}
