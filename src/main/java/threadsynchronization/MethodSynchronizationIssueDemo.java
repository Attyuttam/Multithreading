package main.java.threadsynchronization;

public class MethodSynchronizationIssueDemo {
    private static int counter1 = 0;
    private static int counter2 = 0;

    public static void main(String[] args){
        Thread one = new Thread(() -> {
            for(int i=0;i<10000;i++){
                increment1();
            }
        });
        Thread two = new Thread(() -> {
            for(int i=0;i<10000;i++){
                increment2();
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Counter value 1: "+counter1+" 2: "+counter2);
    }
    /*
    * Although, thread 1 has no use of the increment2() block and similarly for thread 2
    * Still when thread 1 blocks because of the synchronized block at increment1, thread 2 remains blocked for no reason and vice versa
    * This is because the method level lock is applied and since an intrinsic lock is provided per class, thus, only one lock is present
    * and thus, the issue
    * */
    private synchronized static void increment1(){
        counter1++;
    }
    private synchronized static void increment2(){
        counter2++;
    }
}
