package main.java.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemoSummation {
    public static void main(String[] args){
        int[] arr = new int[1000];
        Random random = new Random();
        for(int i=0;i<1000;i++){
            arr[i] = random.nextInt(10)+1;
        }
        System.out.println("Sum of array by simple method: "+ Arrays.stream(arr).sum());

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SummationTask summationTask = new SummationTask(arr,0,arr.length-1);
        Integer sum = forkJoinPool.invoke(summationTask);

        System.out.println("Sum of array by FJ method: "+ sum);

    }
}
class SummationTask extends RecursiveTask<Integer> {
    final int[] arr;
    final int start;
    final int end;

    SummationTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if(end - start + 1>50){
            int mid = start + (end - start)/2;
            SummationTask task1 = new SummationTask(arr,start,mid);
            SummationTask task2 = new SummationTask(arr,mid+1,end);

            task1.fork();
            task2.fork();

            return task1.join() + task2.join();
        }else{
            return sum();
        }
    }

    private Integer sum() {
        int sumval = 0;
        for(int i=start;i<=end;i++){
            sumval+=arr[i];
        }
        return sumval;
    }
}