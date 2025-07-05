package main.java.otherconcepts;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {
    private final Lock lockA = new ReentrantLock(true);
    private final Lock lockB = new ReentrantLock(true);

    public void workerOne(){
        lockA.lock();
        System.out.println("Worker one acquired lockA");

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lockB.lock();
        System.out.println("Worker one acquired lockB");
        lockA.unlock();
        lockB.unlock();
    }
    public void workerTwo(){
        lockB.lock();
        System.out.println("Worker two acquired lockB");

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lockA.lock();
        System.out.println("Worker two acquired lockA");
        lockA.unlock();
        lockB.unlock();
    }
    public static void main(String[] args){
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        new Thread(deadLockDemo::workerOne, "Worker One").start();
        new Thread(deadLockDemo::workerTwo, "Worker Two").start();

        //using MXBean
        new Thread(() -> {
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            while(true){
                long[] threadIds = threadMXBean.findDeadlockedThreads();
                if(threadIds!=null){
                    System.out.println("Deadlock detected");
                    for(long threadId: threadIds){
                        System.out.println("Thread with ID: "+threadId+" is in deadlock. ThreadInfo: "+threadMXBean.getThreadInfo(threadId));
                    }
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
