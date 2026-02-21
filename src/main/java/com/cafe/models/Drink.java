package com.cafe.models;

public class Drink{
    //Create Private Variables for Drinks
    private String drinkName;
    private String recipe;

    public Drink(String drinkName, String recipe){
        this.drinkName = drinkName;
        this.recipe = recipe;
    }


    public String getName() {
        return drinkName;
    }

    public String getRecipe(){

        return recipe;
    }
}

//Drink Coffee = new Drink("name", "Recipe");
//Drink Latte = new Drink("name", "Recipe");
//Drink Matcha = new Drink("name", "Recipe");
