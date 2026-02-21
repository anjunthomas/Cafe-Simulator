package com.cafe.managers;

import com.cafe.models.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryManager {
    private HashMap<String, Ingredient> ingredients;

    public InventoryManager(){
        this.ingredients = new HashMap <>();
        initialize();
    }

    public void initialize(){
        ingredients.put("milk", new Ingredient("milk", 10, "milk.png", "milk2.png"));
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

    public Ingredient getIngredient(String name){
        return ingredients.get(name); // to get a specific ingredient
    }

    // to get all the ingredients so UI can display it
    public List<Ingredient> getAllIngredients(){
        return new ArrayList<>(ingredients.values());
    }

    // we want to check if the ingredients are actually available and doesn't use it yet
    public boolean canMakeRecipe(String recipe) {
        String[] requiredIngredients = recipe.split(",");
        for(String ingredient: requiredIngredients){
            if(!hasIngredient(ingredient.trim())) { // trimming the white space if any
                return false; // because we're missing one or more ingredients.
            }
        }
        return true;
    }

    // to actually use the ingredients
    public boolean useIngredients(String recipe){
        if(!canMakeRecipe(recipe)){
            return false; // ingredients are missing so don't use any ingredients
        }
        String[] requiredIngredients = recipe.split(",");
        for(String ingredient : requiredIngredients){
            useIngredient(ingredient.trim());
        }
        return true;
    }

}
