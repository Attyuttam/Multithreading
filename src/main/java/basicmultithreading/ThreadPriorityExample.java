package main.java.basicmultithreading;

public class ThreadPriorityExample {
    public static void main(String[] args){
        /*System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println(Thread.currentThread().getPriority());*/

        //Even in the example below, although main thread has lesser priority that ThreadOne, it will still get more priority in the CPU than ThreadOne
        System.out.println(Thread.currentThread().getName() + "says Hi!");

        Thread one = new Thread(() -> {
            System.out.println("Thread one says Hi as well !");
        });
        one.setPriority(Thread.MAX_PRIORITY);
        one.start();
    }
}
