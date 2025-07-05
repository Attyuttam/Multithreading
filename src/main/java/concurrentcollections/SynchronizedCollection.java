package main.java.concurrentcollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollection {

    public static void main(String[] args) throws InterruptedException {
       /*
       This code shows how using a non-synchronized method messes up the list.
       The list will not end up having 2000 integers as it's expected to have
       But rather end up with an arbitrary number <= 2000

       List<Integer> list = new ArrayList<>();

        Thread one = new Thread(() -> {
            for(int i=0;i<1000;i++){
                list.add(i);
            }
        });
        Thread two = new Thread(() -> {
            for(int i=0;i<1000;i++){
                list.add(i);
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println(list.size());*/



        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        Thread one = new Thread(() -> {
            for(int i=0;i<1000;i++){
                list.add(i);
            }
        });
        Thread two = new Thread(() -> {
            for(int i=0;i<1000;i++){
                list.add(i);
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println(list.size());
    }
}
