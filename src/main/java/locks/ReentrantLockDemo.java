package main.java.locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private int sharedData = 0;

    public void methodA(){
        reentrantLock.lock();
        try{
            sharedData++;
            System.out.println("Method A: sharedData = "+sharedData);
            methodB();
        }finally{
            reentrantLock.unlock();
        }
    }
    public void methodB(){
        reentrantLock.lock();
        try{
            sharedData--;
            System.out.println("Method B: sharedData = "+sharedData);
        }finally{
            reentrantLock.unlock();
        }
    }
    public static void main(String[] args){
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        for(int i=0;i<5;i++){
            new Thread(reentrantLockDemo::methodA).start();
        }
    }
}
