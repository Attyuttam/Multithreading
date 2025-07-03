package main.java.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0;i<7;i++){
            service.execute(new TaskOne(i));
        }
    }
}
class TaskOne implements Runnable{
    private final int taskId;

    TaskOne(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task ID "+taskId+" being executed by thread "+Thread.currentThread().getName());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
