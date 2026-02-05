package com.cafe.models;

public class RegularCustomer extends Customer {
    // regular customers patience decrements normally
    // they also placce normal orders
    String name;
    int maxPatience;

    public RegularCustomer(String name, int maxPatience){
        super(name, maxPatience);
    }
}
