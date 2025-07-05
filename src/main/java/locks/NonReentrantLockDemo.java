package main.java.locks;

import java.util.concurrent.locks.ReentrantLock;

/*
* In this demo, I basically want to show the same example used in ReentrantLockDemo code but with an intrinsic lock
* */
public class NonReentrantLockDemo {
    private final Object lock = new Object();
    private int sharedData = 0;

    public void method_A(){
        synchronized (lock){
            sharedData++;
            System.out.println("Method A: sharedData = "+sharedData);
            method_B();
        }
    }
    public void method_B(){
        synchronized (lock){
            sharedData--;
            System.out.println("Method B: sharedData = "+sharedData);
        }
    }
    public static void main(String[] args){
        NonReentrantLockDemo nonReentrantLockDemo = new NonReentrantLockDemo();
        for(int i=0;i<5;i++){
            new Thread(nonReentrantLockDemo::method_A).start();
        }
    }
}
