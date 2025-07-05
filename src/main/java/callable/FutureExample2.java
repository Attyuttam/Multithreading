package main.java.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample2 {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            Thread.sleep(5000);
            return 42;
        };

        Future<Integer> future = executor.submit(task); // returns immediately

        System.out.println("Is task done: "+future.isDone());
        System.out.println("Task submitted... doing other work");

        Integer result = future.get(); // blocks until the task completes
        System.out.println("Result: " + result); // prints 42
        System.out.println("Is task done: "+future.isDone());
        executor.shutdown();
    }
}
