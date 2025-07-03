package main.java.threadsynchronization;

public class SynchronizationDemo {
    private static int counter = 0;
    public static void main(String[] args){
        Thread one = new Thread(() -> {
            for(int i=0;i<10000;i++){
                increment();
            }
        });
        Thread two = new Thread(() -> {
            for(int i=0;i<10000;i++){
                increment();
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
    //The keyword synchronized ensures that this code is accessed by only one thread at any time
    private synchronized static void increment(){
        //This section is called the critical section
        //Note: every object in java is associated with a monitor lock(aka intrinsic lock) which is used to ensure mutual exclusion
        counter++;
    }
}
