package com.cafe.managers;

import com.cafe.models.Ingredient;

import java.util.HashMap;

public class InventoryManager {
    private HashMap<String, Ingredient> ingredients;

    InventoryManager(){
        this.ingredients = new HashMap <>();
        initialize();
    }

    public void initialize(){
        ingredients.put("milk", new Ingredient("milk", 10, "milk2.png", "milk.png"));
        ingredients.put("espresso", new Ingredient("espresso", 10, "coffeebeans.png", "coffeebeans2.png"));
        ingredients.put("cups", new Ingredient("cups", 10, "Cups_rack.png", "Cups_rack2.png"));
        ingredients.put("sugar", new Ingredient("sugar", 10, "SugarBowl.png", "SugarBowl2.png"));
    }


}
