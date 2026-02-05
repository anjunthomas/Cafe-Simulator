package com.cafe.models;

public class RegularCustomer extends Customer {
    // regular customers patience decrements normally
    // they also placce normal orders

    public RegularCustomer(String name, int maxPatience, String spritePath){
        super(name, maxPatience, spritePath);
    }
}
