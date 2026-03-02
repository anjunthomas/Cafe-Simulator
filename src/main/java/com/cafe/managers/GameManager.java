package com.cafe.managers;

import com.cafe.managers.InventoryManager;
import com.cafe.models.Drink;
import com.cafe.models.Customer;

import java.util.List;

public class GameManager {
    private int satisfiedCount = 0;
    private Drink latte;
    private Drink coffee;

    private Drink matchaLatte;

    private Drink coldBrew;
    private InventoryManager inventoryManager;

    private CustomerManager customerManager;
    private long lastCustomerLeftTime = 0;
    private final long SPAWN_DELAY = 3000;

    private List<Drink> availableDrinks;

    public GameManager() {
        this.latte = new Drink("Latte", "milk,espresso,cups");
        this.coffee = new Drink("Coffee", "espresso,cups");
        this.matchaLatte = new Drink("Matcha Latte", "matcha,milk,cups");
        this.coldBrew = new Drink("Cold Brew", "water,espresso,cups");
        this.inventoryManager = new InventoryManager();

        String[] sprites = {"fox.png", "deer.png", "penguin.png", "cat.png"};
        Drink[] drinks = {matchaLatte, coldBrew, coffee, latte};
        this.customerManager = new CustomerManager(sprites, drinks);
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public int getSatisfiedCount() {
        return satisfiedCount;
    }

    public Customer getCurrentCustomer() {
        return customerManager.getCurrentCustomer();
    }

    public void update() {
        // Update current customer's patience
        customerManager.updateCustomer();

        // If no customer and 3 seconds have passed, spawn new one
        if (!customerManager.hasCustomer() &&
                System.currentTimeMillis() - lastCustomerLeftTime > SPAWN_DELAY) {
            customerManager.spawnCustomer();
            lastCustomerLeftTime = System.currentTimeMillis();
        }
    }

    public void removeCurrentCustomer() {
        customerManager.removeCurrentCustomer();
        lastCustomerLeftTime = System.currentTimeMillis();
    }



    public List<Drink> getAvailableDrinks() {
        return availableDrinks;
    }

    // this is where we'll check to see if the customer's order's recipe matches the recipe that we're attempting to serve (playerRecipe)
    public boolean serveCustomer(Customer customer, String playerRecipe) {

        String customerOrder = customer.getOrder().getRecipe();

        String[] customerIngredients = customerOrder.split(",");
        String[] playerIngredients = playerRecipe.split(",");

        java.util.Arrays.sort(customerIngredients);
        java.util.Arrays.sort(playerIngredients);

        //inventoryManager.useIngredients(playerRecipe);

        if (!java.util.Arrays.equals(customerIngredients, playerIngredients)) {
            customer.setPatience(0);
            return false;
        }


        //customerManager.removeCurrentCustomer();
        //lastCustomerLeftTime = System.currentTimeMillis();
        satisfiedCount++;
        return true;
    }
}
