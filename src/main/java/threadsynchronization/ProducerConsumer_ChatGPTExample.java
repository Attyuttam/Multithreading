package main.java.threadsynchronization;

public class ProducerConsumer_ChatGPTExample {
    public static void main(String[] args){
        SharedResource sharedResource = new SharedResource();
        Thread producer = new Thread(() -> {
            try {
                int sequence = 0;
                while(sequence < 101){
                    sharedResource.produce(sequence++);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                while(true){
                    sharedResource.consume();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        consumer.start();
    }
}
class SharedResource {
    private int data;
    private boolean hasValue = false;

    synchronized void produce(int value) throws InterruptedException {
        while (hasValue) {
            System.out.println("data value not yet consumed, producer waiting");
            wait(); // wait if resource is full
        }
        data = value;
        hasValue = true;
        System.out.println("Produced: " + value);
        notify(); // notify consumer
    }

    synchronized int consume() throws InterruptedException {
        while (!hasValue) {
            System.out.println("No value in data, consumer waiting");
            wait(); // wait if resource is empty
        }
        hasValue = false;
        System.out.println("Consumed: " + data);
        notify(); // notify producer
        return data;
    }
}

