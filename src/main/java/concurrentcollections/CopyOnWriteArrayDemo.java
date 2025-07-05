package main.java.concurrentcollections;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayDemo {
    public static void main(String[] args){

        Simulation simulation = new Simulation();
        simulation.simulate();

    }
}
class Simulation{
    private final List<Integer> list;

    Simulation() {
        this.list = new CopyOnWriteArrayList<>();
        this.list.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
    }
    public void simulate(){
        Random random = new Random();
        Thread one = new Thread(new WriteTask(list, random));
        Thread two = new Thread(new WriteTask(list, random));
        Thread three = new Thread(new WriteTask(list, random));
        Thread four = new Thread(new WriteTask(list, random));
        Thread five = new Thread(new ReadTask(list));

        one.start();
        two.start();
        three.start();
        four.start();
        five.start();
    }
}
class ReadTask implements Runnable{
    private final List<Integer> list;

    ReadTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(list);
        }
    }
}

class WriteTask implements Runnable{
    private final List<Integer> list;
    private final Random random;

    WriteTask(List<Integer> list, Random random) {
        this.list = list;
        this.random = random;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.set(random.nextInt(list.size()), random.nextInt(10));
        }
    }
}
