package main.java.threadsynchronization;

public class OrderedNumberPrintingDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            try {
                counter.increment("Thread-1", 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                counter.increment("Thread-2", 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
class Counter {
    private int count = 0;
    private final int MAX_COUNT = 20;
    private int turn = 0; // 0 for Thread-1, 1 for Thread-2

    public void increment(String threadName, int myTurn) throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (turn != myTurn) {
                    wait(); // Not this thread's turn
                }

                if (count >= MAX_COUNT) {
                    notifyAll();
                    break;
                }

                count++;
                System.out.println(threadName + ": " + count);

                // Switch turn
                turn = 1 - turn;
                notifyAll(); // Wake up the other thread
            }
        }
    }
}
