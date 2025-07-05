package main.java.locks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private final Integer MAX_SIZE =5;
    private final Lock lock = new ReentrantLock();
    private final Queue<Integer> buffer = new LinkedList<>();

    /*
    * The nomenclature of the conditions are a bit weird
    * But we will have to look at it from the perspective of the signal
    * bufferNotFull is basically a signal that says that the buffer is not full
    * so when we consume something, we signal on this condition stating that
    * an element has been consumed so the buffer is not full.
    * Similarly, bufferNotEmpty is signalled when the producer produces something stating that
    * an element has been produced thus the buffer is not empty.
    * */
    private final Condition bufferNotFull = lock.newCondition();
    private final Condition bufferNotEmpty = lock.newCondition();

    private void produce(int item){
        lock.lock();
        //processing
        try{
            while(buffer.size() == MAX_SIZE){
                bufferNotFull.await();
            }
            buffer.offer(item);
            System.out.println("Produced : "+item);
            bufferNotEmpty.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            lock.unlock();
        }
    }
    private void consume(){
        lock.lock();
        //processing
        try{
            while(buffer.isEmpty()){
                bufferNotEmpty.await();
            }
            System.out.println("Consumed: "+buffer.poll());
            bufferNotFull.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            lock.unlock();
        }
    }
    public static void main(String[] args){
        ConditionDemo conditionDemo = new ConditionDemo();
        Thread producerThread = new Thread(() -> {
            try {
                for(int i=0;i<10;i++){
                    conditionDemo.produce(i);
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumerrThread = new Thread(() -> {
            try {
                for(int i=0;i<10;i++){
                    conditionDemo.consume();
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producerThread.start();
        consumerrThread.start();
    }
}
