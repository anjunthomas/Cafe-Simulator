package com.cafe.models;

public  class Ingredient {
    private String name;
    private int currentAmount;
    private int maxAmount;
    // full image path
    private String fullImagePath;
    private String emptyImagePath;

    // empty image path
    public Ingredient(String name, int maxAmount, String fullImagePath, String emptyImagePath){
        this.name = name;
        this.maxAmount = maxAmount;
        this.currentAmount = maxAmount; // because we're starting the ingredients at full
        this.fullImagePath = fullImagePath;
        this.emptyImagePath = emptyImagePath;
    }

    // refill button should be greyed out until the ingredient is empty
    public void refill() {
        this.currentAmount = this.maxAmount;
    }

    public boolean isEmpty(int currentAmount){
        return this.currentAmount < 0;
    }

    public void use(){
        this.currentAmount --;
        //Boolean empty = isEmpty();
    }

    public String getImagePath() {
        return "Hello";
    }

    public int getCurrentAmount() {
        return 1;
    }

}
