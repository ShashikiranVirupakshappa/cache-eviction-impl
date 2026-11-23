package com.javabro.mrucache;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class CustomMRUCache {

    private final Map<String, CacheObject> cache = new HashMap<>();
    private static final int CAPACITY = 10;

    public synchronized Map<String, CacheObject> getCache() {
        return new HashMap<>(cache);
    }

    public synchronized CacheObject get(String key) {
        CacheObject cacheObject = cache.get(key);
        if (cacheObject == null)
            return null;
        cacheObject.setLastAccessedAt(LocalDateTime.now());
        cache.put(key, cacheObject);
        return cacheObject;
    }

    public synchronized void put(String key, CacheObject cacheObject) {
        if (cache.containsKey(key)) {
            cacheObject.setLastAccessedAt(LocalDateTime.now());
            cache.put(key, cacheObject);
        } else {
            if (cache.size() < CAPACITY)
                cache.put(key, cacheObject);
            else if(cache.size() == CAPACITY){
                //check for most recently used object to remove
                String recentKey = null;
                Long timeDiff = Long.MAX_VALUE;
                for (Map.Entry<String, CacheObject> entry : cache.entrySet()) {
                    if(entry.getValue().getLastAccessedAt() == null)
                        continue;
                    if (ChronoUnit.SECONDS.between(entry.getValue().getLastAccessedAt(), LocalDateTime.now()) <= timeDiff) {
                        timeDiff = ChronoUnit.SECONDS.between(entry.getValue().getLastAccessedAt(), LocalDateTime.now());
                        recentKey = entry.getKey();
                    }
                }
                System.out.println("recentKey to be removed " + recentKey);
                cache.remove(recentKey);
                cache.put(key, cacheObject);
            }
        }
    }

    public synchronized CacheObject remove(String key) {
        return cache.remove(key);
    }
}