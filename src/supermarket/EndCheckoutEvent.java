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
public class EndCheckoutEvent extends Event {
    Checkout checkout;
    Customer customer;
    
    public EndCheckoutEvent(Checkout checkout) {
        super(EventSim.getClock() + checkout.checkoutQueue.peek().checkoutDuration);
        this.checkout = checkout;
        customer = checkout.checkoutQueue.poll();
    }


    @Override
    public Event happen() {
        customer.leaveTime = EventSim.getClock();
        customer.shop.customerHistory.add(customer);
        if (checkout.checkoutQueue.isEmpty()){
            checkout.isActive = false;
            return null;
        }
        return new BeginCheckoutEvent(checkout);
    }
    
    @Override
    public String toString() {
        return "EndCheckoutEvent{" + getTime() + " cust=" + customer.name
                + " " + customer.checkoutDuration + '}';
    }
}