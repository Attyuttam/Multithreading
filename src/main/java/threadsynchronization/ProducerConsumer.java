package main.java.threadsynchronization;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    public static void main(String[] args){
        Worker worker = new Worker(5,0);
        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        consumer.start();
    }
}
class Worker{
    private int sequence = 0; //sequential numbers that will be stored in the shared container
    private final Integer top; //max number of elements that can be stored in the shared container
    private final Integer bottom; //min number of elements that can be stored in the shared container
    private final List<Integer> container;
    private final Object lock = new Object();

    Worker(Integer top, Integer bottom) {
        this.top = top;
        this.bottom = bottom;
        container = new ArrayList<>();
    }


    public void produce() throws InterruptedException {
        synchronized (lock){
            while(true){
                if(container.size() == top){
                    System.out.println("Container full, waiting for items to be removed");
                    lock.wait();
                }else {
                    System.out.println(sequence+" added to the container");
                    container.add(sequence++);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
    public void consume() throws InterruptedException {
        synchronized (lock){
            while(true){
                if(container.size() == bottom){
                    System.out.println("Container empty, waiting for items to be added");
                    lock.wait();
                }else{
                    System.out.println("Consumed value "+container.remove(0)+" from the container");
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}
