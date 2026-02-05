package com.cafe.models;

public class WantsExtraSugarCustomer extends Customer{
    // These customers have the same patience behavior as regular customers
    // they will request extra sugar with their drinks (2) this needs to be implemented once the order class has been created
    public WantsExtraSugarCustomer(String name, int maxPatience) {
        super(name, maxPatience);
    }
}
