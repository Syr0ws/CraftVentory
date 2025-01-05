package com.github.syr0ws.craftventory.api.util;

import java.util.Optional;

/**
 * Represents a container used to transfer data between objects and that stores data as
 * key-value pairs with type safety. Each value in the context is associated with a unique
 * key and a specific Java class type.
 */
public interface Context {

    /**
     * Adds a new data represented as a key-value pair to the context.
     *
     * @param <T>  The type of the value being stored.
     * @param key  The unique key that identifies the data in the context. Must not be {@code null}.
     * @param data The value to store.
     * @param type The Java class type of the value. Must not be {@code null}.
     */
    <T> void addData(String key, T data, Class<T> type);

    /**
     * Checks whether the context contains data associated with the given key.
     *
     * @param key The unique key that identifies the data in the context. Must not be {@code null}.
     * @param type The {@link Class} type of the data. Must not be {@code null}.
     * @return {@code true} if there is a value associated with the given key, otherwise {@code false}.
     */
    boolean hasData(String key, Class<?> type);

    /**
     * Retrieves the value associated with the given key from the context.
     *
     * @param <T>  The expected type of the value.
     * @param key  The unique key that identifies the data in the context. Must not be {@code null}.
     * @param type The Java class type of the value. Must not be {@code null}.
     * @return An {@link Optional} containing the data entry if it exists, or an empty {@link Optional} if not found.
     * @throws NullPointerException If there is no data associated with the given key.
     * @throws IllegalArgumentException If the provided type does not match the type of the stored data.
     */
    <T> Optional<T> getData(String key, Class<T> type);
}
