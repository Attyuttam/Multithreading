package main.java.callable;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //executorService.execute(new ReturnValueTask()); // execute works for classes that have implemented Runnable
        Future<Integer> result = executorService.submit(new ReturnValueTask()); //with submit we can use Runnable as well as Callable

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
/*class ReturnValueTask implements Runnable{

    @Override
    public void run() {
        return 3;
        *//*You see that here we have a compilation error since the run() method returns void,
        so instead we will make the class ReturnValueTask implement the Callable interface
         *//*
    }
}*/
class ReturnValueTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        Thread.sleep(4000);
        return 12;
    }
}