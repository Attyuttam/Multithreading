package main.java.threadsynchronization;

public class RaceConditionDemo {
    private static int counter = 0;
    public static void main(String[] args){
        /*
        * In the code below, we can see that the behavior is different everytime
        * Sometimes the counter will print 12345 and sometimes 11342
        * That means that although in total both the threads are incrementing the counter value a total of 20000 times,
        * The counter value does not get incremented in a deterministic manner.
        *
        * This is because the process of incrementing the counter value essentially contains 3 steps:
        * 1. Load counter value
        * 2. Increment counter value
        * 3. Assign incremented counter value to counter
        *
        * Thus, since the counter param is being used by both the threads, its a shared resource but since the resource
        * is not protected, the value assigned by one thread can be different from the other.
        * Example, thread 1 accesses 0 and increases it to 1 but before assigning 1, thread 2 accesses the coutner, gets 0 and thus, essentially both of them assigns counter as 1 which is incorrect.
        *
        * This phenomenon is called race condition.
        * */
        Thread one = new Thread(() -> {
            for(int i=0;i<10000;i++){
                counter++;
            }
        });
        Thread two = new Thread(() -> {
            for(int i=0;i<10000;i++){
                counter++;
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Counter value: "+counter);
    }
}
