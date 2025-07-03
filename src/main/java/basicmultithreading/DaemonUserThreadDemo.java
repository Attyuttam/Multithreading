package main.java.basicmultithreading;

/*
* User thread are given the priority
* daemon threads run in the background
* Once all the user threads are concluded
* The JVM shuts down the main thread
* and along with that the daemon thread is also forced to be shutdown
* */
public class DaemonUserThreadDemo {
    public static void main(String[] args){
        Thread bgThread = new Thread(new DaemonHelper());
        Thread usrThread = new Thread(new UserThread());

        bgThread.setDaemon(true);

        bgThread.start();
        usrThread.start();
    }
}

class DaemonHelper implements Runnable{

    @Override
    public void run() {
        int count = 0;
        while(count < 500){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            System.out.println("Daemon helper running ....");
        }
    }
}

class UserThread implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User thread done with execution ...");
    }
}
