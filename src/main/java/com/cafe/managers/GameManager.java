package com.cafe.managers;

import com.cafe.models.Drink;

public class GameManager {
    private int satisfiedCount = 0;
    private Drink latte;
    private Drink coffee;
    private InventoryManager inventoryManager;

    public GameManager(){
        this.latte = new Drink("Latte", "milk,espresso,cups");
        this.coffee = new Drink("Coffee", "espresso,cups");
        this.inventoryManager = new InventoryManager();
    }

    public int getSatisfiedCount(){
        return satisfiedCount;
    }

    // first check if we can make the recipe, then use the recipe
    public boolean serveDrink(String recipe){
        boolean success = inventoryManager.useIngredients(recipe);
        if(success){
            satisfiedCount++;
        }
        return success;
    }
}
