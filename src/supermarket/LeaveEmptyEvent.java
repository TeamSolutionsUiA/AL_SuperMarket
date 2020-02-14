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
public class LeaveEmptyEvent extends Event {
    Customer customer;


    public LeaveEmptyEvent(Customer customer) {
        super(EventSim.getClock() + 5);
        this.customer = customer;
    }


    @Override
    public Event happen() {
        customer.leaveTime = EventSim.getClock();
        customer.shop.customers.remove(customer);
        customer.shop.customerHistory.add(customer);
        return null;
    }
    
    @Override
    public String toString() {
        return "LeaveEmptyEvent{" + getTime() + " cust=" + customer.name
                + " " + customer.shoppingDuration + '}';
    }
}
