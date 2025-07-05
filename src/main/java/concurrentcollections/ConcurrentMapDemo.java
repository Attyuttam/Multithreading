package main.java.concurrentcollections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapDemo {
    public static void main(String[] args){
        ConcurrentCache concurrentCache = new ConcurrentCache();
        concurrentCache.start();
    }
}

class ConcurrentCache{
    private static final Map<String, String> cache = new ConcurrentHashMap<>();
    public void start(){
        for(int i=0;i<10;i++){
            final int threadNum = i;
            new Thread(() -> {
                String key = "key @ "+threadNum;
                for(int j=0;j<3;j++){
                    String value = getCachedValue(key);
                    System.out.println("Thread "+Thread.currentThread().getName()+" : key = "+key+" value = "+value);
                }
            }).start();
        }
    }

    private String getCachedValue(final String key) {
        String value = cache.get(key);
        if(value == null){
            value = compute(key);
            cache.put(key, value);
        }
        return value;
    }

    private String compute(String key) {
        System.out.println(key+" not present in the cache, so going to compute !");
        try {
            Thread.sleep(500);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Value for "+key;
    }
}
