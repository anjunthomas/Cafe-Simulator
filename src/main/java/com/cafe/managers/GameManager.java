package com.cafe.managers;

import com.cafe.managers.InventoryManager;
import com.cafe.models.Drink;
import com.cafe.models.Customer;

import java.util.List;

public class GameManager {
    private int satisfiedCount = 0;
    private Drink latte;
    private Drink coffee;
    private InventoryManager inventoryManager;

    private List<Drink> availableDrinks;

    public GameManager() {
        this.latte = new Drink("Latte", "milk,espresso,cups");
        this.coffee = new Drink("Coffee", "espresso,cups");
        this.inventoryManager = new InventoryManager();
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public int getSatisfiedCount() {
        return satisfiedCount;
    }

    public List<Drink> getAvailableDrinks() {
        return availableDrinks;
    }

    // this is where we'll check to see if the customer's order's recipe matches the recipe that we're attempting to serve (playerRecipe)
    public boolean serveCustomer(Customer customer, String playerRecipe) {

        String customerOrder = customer.getOrder().getRecipe();

        if (!customerOrder.equals(playerRecipe)) {
            customer.setPatience(0); // setting the customer to be angry right away
            return false;
        }

        inventoryManager.useIngredients(playerRecipe);
        satisfiedCount++;
        return true;
    }
}
