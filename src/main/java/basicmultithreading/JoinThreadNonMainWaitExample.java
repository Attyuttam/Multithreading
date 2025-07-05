package main.java.basicmultithreading;

public class JoinThreadNonMainWaitExample {
    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            System.out.println("Thread A started");
            try {
                Thread.sleep(2000);
                System.out.println("Thread A finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            System.out.println("Thread B started");
            try {
                threadA.join(); // Thread B waits for A to finish
                System.out.println("Thread B resumed after A finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
    }
}

