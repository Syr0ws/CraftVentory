package com.github.syr0ws.craftventory.common.util;

import com.github.syr0ws.craftventory.api.util.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleContext implements Context {

    private final Map<String, Data<?>> storage = new HashMap<>();

    @Override
    public <T> void addData(String key, T data, Class<T> type) {

        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key cannot be null or empty");
        }

        if(data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        this.storage.put(key, new Data<>(data, type));
    }

    @Override
    public boolean hasData(String key, Class<?> type) {

        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        Data<?> data = this.storage.getOrDefault(key, null);

        return data != null && type.isInstance(data.value());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getData(String key, Class<T> type) {

        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        Data<?> data = this.storage.get(key);

        if (data == null) {
            throw new NullPointerException(String.format("No data found for the key '%s'", key));
        }

        if (type.isInstance(data.value())) {
            throw new IllegalArgumentException(String.format("Type mismatch: %s stored and %s provided", data.type(), type));
        }

        return (T) data.value();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> findData(String key, Class<T> type) {

        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        Data<?> data = this.storage.get(key);

        if (data == null) {
            return Optional.empty();
        }

        if (type.isInstance(data.value())) {
            throw new IllegalArgumentException(String.format("Type mismatch: %s stored and %s provided", data.type(), type));
        }

        return Optional.of((T) data.value());
    }

    private record Data<T>(T value, Class<T> type) {

    }
}
