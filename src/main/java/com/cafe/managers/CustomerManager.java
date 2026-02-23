package com.cafe.managers;

import com.cafe.models.Drink;
import com.cafe.models.Customer;
import com.cafe.models.RegularCustomer;
import com.cafe.models.AngryCustomer;
import com.cafe.models.WantsExtraSugarCustomer;

import java.util.Random;

public class CustomerManager {
    private Customer currentCustomer;
    private String[] spritePaths;
    private Drink[] availableDrinks;
    private Random random; // ← Add this field

    public CustomerManager(String[] spritePaths, Drink[] availableDrinks) {
        this.spritePaths = spritePaths;
        this.availableDrinks = availableDrinks;
        this.random = new Random(); // ← Initialize it
    }

    public void spawnCustomer() {
        // Random sprite (fox, deer, penguin, or cat image)
        String randomSprite = spritePaths[random.nextInt(spritePaths.length)];

        // Random customer BEHAVIOR type (0-2, not 0-3!)
        int behaviorType = random.nextInt(3);

        Customer newCustomer;
        if (behaviorType == 0) {
            newCustomer = new RegularCustomer("Customer", 25, randomSprite);
        } else if (behaviorType == 1) {
            newCustomer = new AngryCustomer("Customer", 25, randomSprite);
        } else {
            newCustomer = new WantsExtraSugarCustomer("Customer", 25, randomSprite);
        }

        // Random drink order
        Drink randomDrink = availableDrinks[random.nextInt(availableDrinks.length)];
        newCustomer.setOrder(randomDrink);

        currentCustomer = newCustomer;
    }

    public void removeCurrentCustomer() {
        currentCustomer = null;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public boolean hasCustomer() {
        return currentCustomer != null;
    }

    public void updateCustomer() {
        if (hasCustomer()) {
            currentCustomer.decreasePatience();

            // Remove if angry
            if (currentCustomer.isAngry()) {
                currentCustomer = null;
            }
        }
    }
}
