package main.java.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(2);
        for(int i=0;i<7;i++){
            service.execute(new Work(i));
        }
    }
}
class Work implements Runnable{
    private final int workId;

    Work(int workId) {
        this.workId = workId;
    }

    @Override
    public void run() {
        System.out.println("Task ID "+workId+" being executed by thread "+Thread.currentThread().getName());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
