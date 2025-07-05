package main.java.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemoWithoutFJConcept {
    public static void main(String[] args){
        int[] arr = new int[100];
        Random random = new Random();
        for(int i=0;i<arr.length;i++){
            arr[i] = random.nextInt(10)+1;
        }
        int searchElement = random.nextInt(10)+1;
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SearchOccurrenceTaskFJ searchOccurrenceTaskFJ = new SearchOccurrenceTaskFJ(arr, 0, arr.length-1,searchElement);
        Integer occurrence = forkJoinPool.invoke(searchOccurrenceTaskFJ);

        System.out.println("Array is: "+ Arrays.toString(arr));
        System.out.printf("%d found %d times",searchElement,occurrence);
    }
}
class SearchOccurrenceTask extends RecursiveTask<Integer> {
    final int[] arr;
    final int start;
    final int end;
    final int searchElement;

    SearchOccurrenceTask(int[] arr, int start, int end, int searchElement) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }

    @Override
    protected Integer compute() {
        return search();
    }
    private Integer search(){
        int count = 0;
        for(int i=start;i<=end;i++){
            if(arr[i] == searchElement){
                count++;
            }
        }
        return count;
    }
}