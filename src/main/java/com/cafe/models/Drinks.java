package com.cafe.models;

public abstract class Drinks {
    String name;
    String recipe;

    public Drinks(String name, String recipe){
        this.name = name;
        this.recipe = recipe;
    }

    public String recipe() {

        return recipe;
    }
    public String getName() {
        return name ;
    }
}
