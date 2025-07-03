package main.java.basicmultithreading;

public class JoinThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            for(int i=0; i<15;i++){
                System.out.println("Thread 1: "+i);
            }
        });

        Thread two = new Thread(() -> {
            for(int i=0; i<25;i++){
                System.out.println("Thread 2: "+i);
            }
        });


        /*
        one.start();
        two.start();

        //Note: We will observe that this line gets printed at first
        //That is because the main thread has the highest priority
        //Since the CPU takes in the main method and then creates the definition of Thread1 and Thread2
        //Then the CPU starts the 3 threads(1,2 and main) separately
        //Since main has the highest priority, this println statement is executed first.
        System.out.println("Done executing both the threads !");
        */

        //To avoid the issue mentioned in the comment outside, we will be using the concept of join()

        System.out.println("Before executing the threads ...");
        one.start();
        two.start();
        one.join();
        two.join();
        System.out.println("Done executing both the threads !");
    }
}
