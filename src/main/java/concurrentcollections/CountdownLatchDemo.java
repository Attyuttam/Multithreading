package main.java.concurrentcollections;

import java.util.concurrent.CountDownLatch;

/*
* In this example, we are going to create a simulation where a group of chefs need to prepare different dishes in the
* kitchen. Each chef is responsible for preparing a specific type of dish and the kitchen manager wants to start
* serving the customers once all the dishes are ready.
* */
public class CountdownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        Restaurant restaurant = new Restaurant();
        restaurant.work();
    }
}
class Restaurant{
    public void work() throws InterruptedException {
        int numberOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(numberOfChefs);

        new Thread(new Chef("Chef A", "Pizza", latch)).start();
        new Thread(new Chef("Chef B", "Pasta", latch)).start();
        new Thread(new Chef("Chef C", "Salad", latch)).start();

        latch.await();

        System.out.println("All dished ready !");
    }
}
class Chef implements Runnable{
    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    Chef(String name, String dish, CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    @Override
    public void run() {

        try {
            System.out.println(name+" is preparing "+dish);
            Thread.sleep(2000);
            System.out.println(name+" has finished preparing "+dish);
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
