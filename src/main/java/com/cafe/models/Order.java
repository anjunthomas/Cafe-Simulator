//Keep track of Orders
//Connect customer with Order (Drink) - Inventory Manager

package com.cafe.models;

public class Orders{

private Drinks order;
private Customer customer;
private Boolean checkOrder;


public order(Customer customer, Drinks order){
    //Orders obj = new Orders(); Where should I create the new object?
    this.checkOrder = false;
    this.customer = customer;
    this.order = order;

}

public Customer getCustomer() {
        return this.customer;
    }

public Drinks getOrder() {
        return this.order;
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
