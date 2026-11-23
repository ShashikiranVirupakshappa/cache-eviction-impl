package com.javabro.lrucache;

import java.time.LocalDateTime;

public class LRUCacheClientApp {
    public static void main(String[] args) {
        int capacity = 10;
        CustomLRUCache customCache = new CustomLRUCache();

        //fill cache objects to map
        for(int i=0;i<capacity;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            customCache.put("key"+i, new CacheObject("key"+i, "value"+i, LocalDateTime.now()));
        }

        System.out.println(customCache.get("key1"));
        System.out.println(customCache.get("key2"));
        System.out.println(customCache.get("key6"));
        System.out.println(customCache.get("key9"));

        for(int i=100;i<105;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            customCache.put("key"+i, new CacheObject("key"+i, "value"+i, LocalDateTime.now()));
        }

        System.out.println(customCache.getCache());
    }
}
