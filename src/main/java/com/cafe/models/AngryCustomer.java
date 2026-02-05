package com.cafe.models;

public class AngryCustomer extends Customer {
    // Angry customers loss patience faster
    // their order is the same as a regular customer
    public AngryCustomer(String name, int maxPatience, String spritePath) {
        super(name, maxPatience, spritePath);
    }

    @Override
    public void decreasePatience() {
        this.patience -= 2;
    }
}
