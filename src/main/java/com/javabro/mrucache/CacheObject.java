package com.javabro.mrucache;

import java.time.LocalDateTime;

public class CacheObject {
    private String key;
    private String value;
    private LocalDateTime createdAt;
    private LocalDateTime lastAccessedAt;

    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }

    public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CacheObject(String key, String value, LocalDateTime createdAt) {
        this(key, value, createdAt, null);
    }

    private CacheObject(String key, String value, LocalDateTime createdAt, LocalDateTime lastAccessedAt) {
        this.key=key;
        this.value=value;
        this.createdAt=createdAt;
        this.lastAccessedAt=lastAccessedAt;
    }
}