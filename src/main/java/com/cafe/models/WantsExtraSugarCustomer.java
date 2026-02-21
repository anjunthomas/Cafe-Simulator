package com.cafe.models;

public class WantsExtraSugarCustomer extends Customer{
    // These customers have the same patience behavior as regular customers
    // they will request extra sugar with their drinks (2) this needs to be implemented once the order class has been created
    public WantsExtraSugarCustomer(String name, int maxPatience, String spritePath) {
        super(name, maxPatience, spritePath);
    }

    @Override
    public void setOrder(Drink drink){
        // to add 2 extra sugar to their drink, this will be randomized later between 1 and 2
        String modifiedRecipe = drink.getRecipe() + ",sugar,sugar";
        Drink customDrink = new  Drink(drink.getName() + " (Extra Sugar)", modifiedRecipe);
        super.setOrder(customDrink);
    }
}
