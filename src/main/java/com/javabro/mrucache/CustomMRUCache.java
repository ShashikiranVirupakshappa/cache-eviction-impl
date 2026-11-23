package com.javabro.mrucache;

import com.javabro.cache.model.CacheObject;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomMRUCache {

    private final Map<String, CacheObject> cache = new LinkedHashMap<>();
    private static final int CAPACITY = 10;

    public synchronized Map<String, CacheObject> getCache() {
        return new LinkedHashMap<>(cache);
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
        if(key == null)
            throw new IllegalArgumentException("key cannot be null");
        if(cacheObject ==null)
            throw new NullPointerException("Cache object is null");
        if(!key.equals(cacheObject.getKey()))
            throw new IllegalArgumentException("Cache key does not match");
        if (!cache.containsKey(key)) {
            if (cache.size() >= CAPACITY) {
                //check for most recently used object to remove
                String recentKey = null;
                long timeDiff = Long.MAX_VALUE;
                for (Map.Entry<String, CacheObject> entry : cache.entrySet()) {
                    if (entry.getValue().getLastAccessedAt() == null)
                        continue;
                    if (ChronoUnit.MILLIS.between(entry.getValue().getLastAccessedAt(), LocalDateTime.now()) <= timeDiff) {
                        timeDiff = ChronoUnit.MILLIS.between(entry.getValue().getLastAccessedAt(), LocalDateTime.now());
                        recentKey = entry.getKey();
                    }
                }
                System.out.println("recentKey to be removed " + recentKey);
                cache.remove(recentKey);
            }
        }
        cacheObject.setLastAccessedAt(LocalDateTime.now());
        cache.put(key, cacheObject);
    }

    public synchronized CacheObject remove(String key) {
        return cache.remove(key);
    }
}