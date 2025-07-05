package main.java.locks;

public class MyLockDemo {
    private static int counter = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread one  = new Thread(() -> {
            for(int i=1;i<=5;i++)
                incrementCounter();
        });
        Thread two  = new Thread(() -> {
            for(int i=1;i<=5;i++)
                incrementCounter();
        });
        one.start();
        two.start();
        one.join();
        two.join();
        System.out.println("Counter updated");
    }
    private static void incrementCounter() {
        synchronized (lock){
            counter++;
            System.out.println("Counter: "+counter);
        }
    }
}

