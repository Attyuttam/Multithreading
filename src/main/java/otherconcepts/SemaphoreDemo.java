package main.java.otherconcepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args){
        Scraper scraper = new Scraper();
        scraper.performScraping();
    }
}
enum ScrapeService{
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3);
    public void scrape(){
        try {
            semaphore.acquire();
            invokeScrapeBot();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            semaphore.release();
        }
    }

    private void invokeScrapeBot() {
        try{
            System.out.println("Scraping data...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class Scraper{
    public void performScraping(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=1;i<=15;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    ScrapeService.INSTANCE.scrape();
                }
            });
        }
    }
}

