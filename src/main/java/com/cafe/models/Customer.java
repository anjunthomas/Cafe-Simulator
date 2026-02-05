package com.cafe.models;

public abstract class Customer { // this is the customer base class
    private String name;
    private Drinks order;
    protected int patience; // so subclasses can access it too (we have AngryCustomer and wantsExtraSugarCustomer)
    private int maxPatience;
    private String spritePath;

    public String getName(){
        return this.name;
    }

    public Drinks getOrder() { // to let other classes read this data, (getter), necessary since the class attributes are private
        return this.order;
    }

    public String getSpritePath(){
        return this.spritePath;
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

    public Customer(String name, int maxPatience, String spritePath){
        this.name = name;
        this.maxPatience = maxPatience;
        this.patience = maxPatience;
        this.spritePath = spritePath;
    }

    public void decreasePatience(){
        this.patience--;
    }

    public boolean isAngry(){
        return this.patience <= 0;
    }
}
