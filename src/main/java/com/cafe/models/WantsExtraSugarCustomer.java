package com.cafe.models;

import java.util.Random;

public class WantsExtraSugarCustomer extends Customer{
    private static Random random = new Random();
    // These customers have the same patience behavior as regular customers
    // they will request extra sugar with their drinks (2) this needs to be implemented once the order class has been created
    public WantsExtraSugarCustomer(String name, int maxPatience, String spritePath) {
        super(name, maxPatience, spritePath);
    }

    @Override
    public void setOrder(Drink drink){
        // to add 2 extra sugar to their drink, this will be randomized later between 1 and 2
        int numSugars = random.nextInt(2) + 1;

        String modifiedRecipe = drink.getRecipe();
        for(int i = 0; i < numSugars; i++){
            modifiedRecipe += ",sugar";
        }
        String sugarText = numSugars == 1 ? "(1 Extra Sugar)" : "(2 Extra Sugars)";
        Drink customDrink = new  Drink(drink.getName() + " " + sugarText, modifiedRecipe);
        super.setOrder(customDrink);
    }
}
