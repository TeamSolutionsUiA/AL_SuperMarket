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
public class BeginCheckoutEvent extends Event {
    Checkout checkout;
    Customer customer;


    public BeginCheckoutEvent(Checkout checkout) {
        super(EventSim.getClock() + 5);
        this.checkout = checkout;
        customer = checkout.checkoutQueue.peek();
    }


    @Override
    public Event happen() {
        customer.checkoutTime = EventSim.getClock();
        customer.queueWaitDuration = customer.checkoutTime - customer.endShoppingTime;
        return new EndCheckoutEvent(checkout);
    }
    
    @Override
    public String toString() {
        return "BeginCheckoutEvent{" + getTime() + "checkout=" + checkout.name + " cust=" + customer.name
                + " " + customer.queueWaitDuration + '}';
    }
    
}
