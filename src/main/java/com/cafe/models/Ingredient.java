//Ingredients Class

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

    public boolean isEmpty(){
        return this.currentAmount <= 0;  //Check if the ingredient is 0 or backup case: less than Zero
    }

    public void use(){
        this.currentAmount --;
        //Call Empty function to check if ingredient is emp
        Boolean empty = isEmpty();
        
    //Future Implementation: Check if empty variable is negative and print error message
      //  if(empty < 0){
            
      //  }
    }

    public String getImagePath() {
        if (this.currentAmount > 0) {
            return this.fullImagePath;
        } else {
            return this.emptyImagePath;
        }
    }

    public int getCurrentAmount() {
        return this.currentAmount;
    }

    public String getName(){
        return name;
    }

}
