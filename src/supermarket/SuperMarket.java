/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;
import eventsim.EventSim;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author evenal
 */
public class SuperMarket {

    public static void main(String[] arts) {
        SuperMarket supern = new SuperMarket();
        supern.startSim();
    }

    public static final int NUM_CHECKOUTS = 2;
    public static final int NUM_CUSTOMERS = 10;

    Checkout[] checkouts;
    List<Customer> customers;
    List<Event> init;
    List<Customer> customerHistory;


    public SuperMarket() {
        checkouts = new Checkout[NUM_CHECKOUTS];
        for (int i = 0; i < NUM_CHECKOUTS; i++)
            checkouts[i] = new Checkout(this, i);
        customers = new ArrayList<>();
        init = new ArrayList<>();
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            Customer c = new Customer(this, i);
            init.add(new BeginShoppingEvent(c));
            customers.add(c);
        }
        customerHistory = new ArrayList<>();
    }


    public void startSim() {
        EventSim sim = EventSim.getInstance();
        sim.setup(init);
        sim.run();
        System.err.println("-------------------------------------------------");
        System.err.println("Average waiting time: " + getAverageWaitingTime());
        System.err.println("Maximum waiting time: " + getMaxWaitingTime());
        System.err.println("-------------------------------------------------");
    }
    
    public Checkout getCheckout(){
        Checkout shortCheckout = checkouts[0];
        for (Checkout checkout : checkouts) {
            if (checkout.getQueueSize() > shortCheckout.getQueueSize()){
                shortCheckout = checkout;
            }
        }
        return shortCheckout;
    }
    
    private int getAverageWaitingTime(){
        int totalWaitingTime = 0;
        for (Customer customer : customerHistory){
            totalWaitingTime += customer.queueWaitDuration;
        }
        
        return totalWaitingTime / customerHistory.size();
    }
    
    private int getMaxWaitingTime(){
        int maxWaitingTime = 0;
        for (Customer customer : customerHistory){
            if (customer.queueWaitDuration > maxWaitingTime){
                maxWaitingTime = customer.queueWaitDuration;
            }
        }
        return maxWaitingTime;
    }
}
