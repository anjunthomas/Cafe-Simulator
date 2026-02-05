package com.cafe.models;

public class AngryCustomer extends Customer {
    // Angry customers loss patience faster
    // their order is the same as a regular customer
    public AngryCustomer(String name, int maxPatience) {
        super(name, maxPatience);
    }

    @Override
    public void decreasePatience() {
        this.patience -= 2;
    }
}
