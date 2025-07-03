package main.java.threadsynchronization;

import java.util.ArrayList;
import java.util.List;

/*
* I am trying to work on a PC problem via a supermarket checkout problem
* Let's say there are 3 booths in the supermarket where billing is done
* An infinite flow of customers are coming in
* Whichever booth is free, caters to the consumer. Each booth can cater to one customer at a time
* Imitate this scenario
*
* Solution:
* The booths are the consumers
* The customers are the producers
* So, I will spin up 3 consumer threads imitating the 3 booths
* But how to spin up infinite customer(producer) threads ?
* Let's say there are 20 producers ?
* As of now, let's assume that
* */
public class SuperMarket_ProducerConsumer {
    public static void main(String[] args){
        CheckoutSystem checkoutSystem = new CheckoutSystem();

        for(int i=1;i<=3;i++){
            final int boothId = i;
            new Thread(() -> {
                while(true){
                    try {
                        checkoutSystem.caterCustomer(boothId);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
        new Thread(() -> {
            int i = 1;
            while(i<=20){
                try {
                    checkoutSystem.intakeCustomer("Cust_"+i);
                    i++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
class CheckoutSystem{
    List<String> customers = new ArrayList<>();
    private final Object lock = new Object();

    public void caterCustomer(int boothId) throws InterruptedException {
        synchronized (lock){
            while(customers.isEmpty()){
                System.out.println("No customers in booth "+boothId+", waiting...");
                lock.wait();
            }
            String customer = customers.remove(0);
            System.out.println("Booth " + boothId + " attending to customer: " + customer);
            lock.notifyAll();
        }
    }
    public void intakeCustomer(String customerId) throws InterruptedException {
        synchronized (lock){
            while(customers.size() == 3){
                System.out.println("All booths are full, customer " + customerId + " waiting...");
                lock.wait();
            }
            System.out.println("Customer "+customerId+" coming in");
            customers.add(customerId);
            lock.notifyAll();
        }
    }
}
