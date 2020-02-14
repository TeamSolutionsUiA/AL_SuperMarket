/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;
import eventsim.EventSim;

/**
 *
 * @author Are
 */
public class BeginQueueEvent extends Event {
    Customer customer;


    public BeginQueueEvent(Customer customer) {
        super(EventSim.getClock() + 5);
        this.customer = customer;
    }


    @Override
    public Event happen() {
        Checkout checkout = customer.shop.getCheckout();
        customer.shop.customers.remove(customer);
        if (!checkout.addCustomerToQueue(customer)){
            return new BeginCheckoutEvent(checkout);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "BeginQueueEvent{" + getTime() + " cust=" + customer.name
                + '}';
    }

}