package main.java.concurrentcollections;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args){
        MultiStageTour multiStageTour = new MultiStageTour();
        multiStageTour.startTour();
    }
}
/*
* The idea here is, we are going to implement a feature where there is a group of tourists along with a tour guide.
* There are different stages of a tour and during a stage, the tourists are supposed to arrive at a particular location.
* Once all the tourists arrive at that location, then they will move along to the next stage of the tour with the tour guide.
* This is a small simulation to demonstrate the concept of Cyclic barrier.
* */

class MultiStageTour{
    private static final int NUM_TOURISTS = 5;
    private static final int NUM_STAGES = 3;
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_TOURISTS, () -> {
        System.out.println("Tour guide starts speaking ... ");
    });
    static class Tourist implements Runnable {
        private final int touristId;

        public Tourist(int touristId){
            this.touristId = touristId;
        }

        @Override
        public void run() {
            for(int i=0;i<NUM_STAGES;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Tourist "+touristId+" arrives at Stage "+(i+1));
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void startTour(){
        for(int i=0;i<NUM_TOURISTS;i++){
            Thread touristThread = new Thread(new Tourist(i));
            touristThread.start();
        }
    }

}
