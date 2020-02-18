/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author evenal
 */
public class Checkout {
    // amount of time per prouct (to scan barcode)
    public static final int PROD_DURATION = 2;
    // amount of time to pay
    public static final int PAY_DURATION = 10;
    //total time for checkout = PAY_DURATION + PROD_DURATION*customer.numProd

    SuperMarket shop;
    String name;
    Queue<Customer> checkoutQueue;
    Boolean isActive;

    public Checkout(SuperMarket shop, int i) {
        this.shop = shop;
        this.name = "Checkout" + i;
        checkoutQueue = new LinkedList();
        isActive = false;
    }
    
    public boolean addCustomerToQueue(Customer customer){
        checkoutQueue.add(customer);
        customer.checkoutDuration = customer.numProducts * PROD_DURATION + PAY_DURATION;
        if (!isActive) {
            isActive = true;
            return false;
        }
        return true;
    }
    
    public int getQueueSize(){
        return checkoutQueue.size();
    }
}
