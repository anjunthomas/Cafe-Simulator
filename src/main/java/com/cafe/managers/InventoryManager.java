package com.cafe.managers;

import com.cafe.models.Ingredient;

import java.util.HashMap;

public class InventoryManager {
    private HashMap<String, Ingredient> ingredients;

    public InventoryManager(){
        this.ingredients = new HashMap <>();
        initialize();
    }

    public void initialize(){
        ingredients.put("milk", new Ingredient("milk", 10, "milk2.png", "milk.png"));
        ingredients.put("espresso", new Ingredient("espresso", 10, "coffeebeans.png", "coffeebeans2.png"));
        ingredients.put("cups", new Ingredient("cups", 10, "Cups_rack.png", "Cups_rack2.png"));
        ingredients.put("sugar", new Ingredient("sugar", 10, "SugarBowl.png", "SugarBowl2.png"));
    }

    public boolean useIngredient(String name){
        Ingredient ingredient = ingredients.get(name);
        if(ingredient == null){
            return false;
        }
        return ingredient.use(); // returning whether the operation was successful
    }

    public void refillIngredient(String name){
        Ingredient ingredient = ingredients.get(name);
        if(ingredient != null){
            ingredient.refill();
        }
    }

    public boolean hasIngredient(String name){
        Ingredient ingredient = ingredients.get(name);
        if(ingredient == null){
            return false;
        }
        return !ingredient.isEmpty(); // true will be when the ingredient isn't empty, so we're returning false when it is
    }

}
