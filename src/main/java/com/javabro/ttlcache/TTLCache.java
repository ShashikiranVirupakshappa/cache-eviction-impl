package com.javabro.ttlcache;

import com.javabro.cache.model.CacheObject;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

public class TTLCache {

    private final Map<String, CacheObject> cache = new LinkedHashMap<>();
    private static final long FIXED_TIME_TO_LIVE = 120000L; //2 mins Time to live

    public synchronized Map<String, CacheObject> getCache() {
        return new LinkedHashMap<>(cache);
    }

    public synchronized CacheObject get(String key) {
        CacheObject cacheObject = cache.get(key);
        if(cacheObject == null)
            return null;
        else if(ChronoUnit.MILLIS.between(cacheObject.getCreatedAt(), LocalDateTime.now()) > FIXED_TIME_TO_LIVE) {
            cache.remove(key);
            return null;
        }
        return cacheObject;
    }

    public synchronized void put(String key, CacheObject cacheObject) {
        if(key == null)
            throw new IllegalArgumentException("key is null");

        if(cacheObject ==null)
            throw new NullPointerException("Cache object is null");

        if(!key.equals(cacheObject.getKey()))
            throw new IllegalArgumentException("Cache key does not match");

        if(cacheObject.getCreatedAt() == null)
            throw new IllegalArgumentException("Cache object created at cannot be null");

        if(cacheObject.getCreatedAt().isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("Cache object created at cannot be after current timestamp");

        cache.put(key, cacheObject);
    }

    public synchronized CacheObject remove(String key) {
        return cache.remove(key);
    }
}