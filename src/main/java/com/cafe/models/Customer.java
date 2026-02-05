package com.cafe.models;

public class Customer { // every customer we create will be an instance of this Customer class definition
    private String name;
    private Drinks order;
    private int patience;
    private int maxPatience;

    public String getName(){
        return this.name;
    }

    public Drinks getOrder() { // to let other classes read this data, (getter), necessary since the class attributes are private
        return this.order;
    }

    public void setOrder(Drinks order){ // this is a setter to let you write and change the order
        this.order = order;
    }

    public int getPatience(){
        return this.patience;
    }

    public int getMaxPatience(){
        return this.maxPatience;
    }

    public Customer(String name, int maxPatience){
        this.name = name;
        this.maxPatience = maxPatience;
        this.patience = maxPatience;
    }

    public void decreasePatience(){
        this.patience--;
    }

    public boolean isAngry(){
        return this.patience <= 0;
    }
}
