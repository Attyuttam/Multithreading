package main.java.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorDemo {
    public static void main(String[] args){
        ExecutorService service = Executors.newSingleThreadExecutor();
        //using executor service
        for(int i=0;i<5;i++){
            service.execute(new Task(i));
        }

        //without using executor service
        /*for(int i=0;i<5;i++){
            new Thread(new Task(i)).start();
        }*/

    }
}
class Task implements Runnable{
    private final int taskid;


    Task(int taskid) {
        this.taskid = taskid;
    }

    @Override
    public void run() {
        System.out.println("Task with ID "+taskid+" being executed by Thread "+Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        }catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
