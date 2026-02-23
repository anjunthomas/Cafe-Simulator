package com.cafe.managers;

import com.cafe.managers.InventoryManager;
import com.cafe.models.Drink;
import com.cafe.models.Customer;
import com.cafe.models.AngryCustomer;
import com.cafe.models.RegularCustomer;
import com.cafe.models.WantsExtraSugarCustomer;

import java.util.List;
import java.util.Random;

//Task 1
public class CustomerManager{
    //Create current Customer field
    private Customer currentCustomer;

    //Create Arrays for sprite paths and available drinks
    private String[] spritePaths;
    private Drink[] availableDrinks;

    //Task 2
    public CustomerManager(String[] spritePaths, Drink[] availableDrinks){
        this.spritePaths = spritePaths;
        this.availableDrinks = availableDrinks;

    }

    //Task 3
    public void spawnCustomer(){
        String randomSprite = spritePaths[random.nextInt(spritePaths.length)];
        Drink randomDrink = availableDrinks[random.nextInt(availableDrinks.length)];

        int type = random.nextInt(2); //Randomly pick a new type of customer
         if(type == 0){
              new Customer = new RegularCustomer(name, 10, randomSprite);
        } else if(type == 1){
              new Customer = new AngryCustomer(name, 5, randomSprite);
        } else(type == 2){
              new Customer = new WantsExtraSugarCustomer(name, 10, randomSprite);
        }
        Customer.setOrder(randomDrink); //Generate a random drink for each new customer
        currentCustomer = Customer; 
    }

    //Task 4
    public void removeCustomer(){
        currentCustomer = null;
    }

    //Task 5
    public Customer getCurrentCustomer(){
        return currentCustomer;
    }

    //Task 6
    public boolean hasCustomer(){
        //Check is customer exists
        if(currentCustomer != null){
            return true;
        } else{
            return false;
        }
    }

    public void UpdateCustomer(){
        if(currentCustomer != null){
            currentCustomer.decreasePatience();
        }
    }
}
