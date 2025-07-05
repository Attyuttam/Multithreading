package main.java.callable;

import java.util.concurrent.*;

//This example is not from the multithreading course but from ChatGPT

/*
* Note that Future is a blocking operation.
* If we hit get() on the future variable and nothing is returned, then the thread will be blocked and the operations will be stalled
* */
public class FutureExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            Thread.sleep(1000);
            return 42;
        };

        Future<Integer> future = executor.submit(task); // returns immediately

        System.out.println("Task submitted... doing other work");

        Integer result = future.get(); // blocks until the task completes
        System.out.println("Result: " + result); // prints 42

        executor.shutdown();
    }
}

