//Keep track of Orders
//Connect customer with Order (Drink) - Inventory Manager
package com.cafe.models;

public class Order{
    private Drink orderedDrink;
    private Customer customer;
    private Boolean checkOrder;

    public Order(Customer customer, Drink orderedDrink){
        this.checkOrder = false;
        this.customer = customer;
        this.orderedDrink = orderedDrink;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Drink getOrder() {
        return this.orderedDrink;
    }

    public Boolean getCheckOrder(){
        return this.checkOrder;
    }

    //Checks if order is Complete
    public boolean isComplete(){
        return this.checkOrder;
    }

    public void complete(){
        this.checkOrder = true;
    }
}
