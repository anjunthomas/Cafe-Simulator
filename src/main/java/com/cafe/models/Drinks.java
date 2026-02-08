package com.cafe.models;

public abstract class Drinks {
    String drinkName;
    String recipe;

    public Drinks(String drinkName, String recipe){
        this.drinkName = drinkName;
        this.recipe = recipe;
    }

    public String recipe() {

        return recipe;
    }
    public String getName() {
        return name ;
    }
}
