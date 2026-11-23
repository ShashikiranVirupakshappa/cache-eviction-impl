package com.javabro.cache.model;

import java.time.LocalDateTime;
import java.util.Objects;

public final class CacheObject {
    private final String key;
    private final String value;
    private final LocalDateTime createdAt;
    private LocalDateTime lastAccessedAt;

    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }

    public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
        if(lastAccessedAt == null)
            this.lastAccessedAt = LocalDateTime.now();
        else
            this.lastAccessedAt = lastAccessedAt;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CacheObject(String key, String value, LocalDateTime createdAt) {
        this(key, value, createdAt, LocalDateTime.now());
    }

    private CacheObject(String key, String value, LocalDateTime createdAt, LocalDateTime lastAccessedAt) {
        this.key=key;
        this.value=value;
        this.createdAt=createdAt;
        this.lastAccessedAt=lastAccessedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CacheObject that = (CacheObject) o;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value) && Objects.equals(createdAt, that.createdAt) && Objects.equals(lastAccessedAt, that.lastAccessedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, createdAt, lastAccessedAt);
    }

    /*@Override
    public String toString() {
        return "CacheObject{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", createdAt=" + createdAt +
                ", lastAccessedAt=" + lastAccessedAt +
                '}';
    }*/
}