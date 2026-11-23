package com.javabro.ttlcache;

import com.javabro.cache.model.CacheObject;

import java.time.LocalDateTime;

public class TTLCacheClientApp {
    public static void main(String[] args) {
        TTLCache ttlCache = new TTLCache();

        //fill cache objects to map
        for(int i=0;i<10;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ttlCache.put("key"+i, new CacheObject("key"+i, "value"+i, LocalDateTime.now()));
        }
        System.out.println(ttlCache.get("key1"));
        System.out.println(ttlCache.get("key2"));
        System.out.println(ttlCache.get("key6"));
        System.out.println(ttlCache.get("key9"));
        System.out.println("sleep started");
        try {
            Thread.sleep(150000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("sleep ended");
        System.out.println(ttlCache.get("key1"));
        System.out.println(ttlCache.get("key2"));
        System.out.println(ttlCache.get("key6"));
        System.out.println(ttlCache.get("key9"));

        for(int i=100;i<105;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ttlCache.put("key"+i, new CacheObject("key"+i, "value"+i, LocalDateTime.now()));
        }

        System.out.println(ttlCache.getCache());
    }
}
