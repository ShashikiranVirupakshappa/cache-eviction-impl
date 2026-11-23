package com.javabro.mrucache;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MRUCacheClientApp {
    public static void main(String[] args) {
        int capacity = 10;
        CustomMRUCache customCache = new CustomMRUCache();

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
