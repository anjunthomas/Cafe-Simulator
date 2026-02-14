package com.cafe.models;

public class Drink{
    //Create Private Variables for Drinks
    private String drinkName;
    private String Recipe;

    public Drink(String drinkName, String Recipe){
        this.drinkName = drinkName;
        this.Recipe = Recipe;
    }

   //Set Getters to Get Recipe and Name
    public String getRecipe() {

        return recipe;
    }
    public String getName() {

        return drinkName;
    }
}

//Drink Coffee = new Drink("name", "Recipe");
//Drink Latte = new Drink("name", "Recipe");
//Drink Matcha = new Drink("name", "Recipe");
