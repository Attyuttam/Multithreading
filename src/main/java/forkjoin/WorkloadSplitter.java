package main.java.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class WorkloadSplitter extends RecursiveAction  {
    private final long workLoad;

    public WorkloadSplitter(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        if(workLoad > 16){
            System.out.println("Work Load too big! Thus splitting: "+workLoad);
            long firstWorkLoad = workLoad/2;
            long secondWorkLoad = workLoad - firstWorkLoad;

            WorkloadSplitter  firstSplit = new WorkloadSplitter(firstWorkLoad);
            WorkloadSplitter  secondSplit = new WorkloadSplitter(secondWorkLoad);

            firstSplit.fork();
            secondSplit.fork();
        }else{
            System.out.println("Work Load within limits! Task being executed for work load: "+workLoad);
        }
    }
}
class WorkloadSplitterDemo{
    public static void main(String[] args){
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        WorkloadSplitter workloadSplitter = new WorkloadSplitter(128);
        forkJoinPool.invoke(workloadSplitter);
    }
}
