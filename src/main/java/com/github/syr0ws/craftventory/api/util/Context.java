package com.github.syr0ws.craftventory.api.util;

import java.util.Optional;

/**
 * Represents a container used to store and transfer data between objects. Each data is stored
 * by key, and type safety is ensured by class type comparison.
 */
public interface Context {

    /**
     * Adds a new data represented as a key-value pair to the context.
     *
     * @param <T>   The type of the value to store.
     * @param key   The unique key that identifies the data in the context. Must not be {@code null}.
     * @param value The value to store.
     * @param type  The {@link Class} type of the data. Must not be {@code null}.
     */
    <T> void addData(String key, T value, Class<T> type);

    /**
     * Checks whether the context contains a data of the given type associated with the given key.
     *
     * @param key The unique key that identifies the data in the context. Must not be {@code null}.
     * @param type The {@link Class} type of the data. Must not be {@code null}.
     * @return {@code true} if there is a value associated with the given key and that can be converted
     * to the given type, otherwise {@code false}.
     */
    boolean hasData(String key, Class<?> type);

    /**
     * Retrieves the value associated with the given key from the context.
     *
     * @param <T>  The expected type of the data.
     * @param key  The unique key that identifies the data in the context. Must not be {@code null}.
     * @param type The {@link Class} type of the data. Must not be {@code null}.
     * @return The data associated with the given key and type if it exists.
     * @throws NullPointerException If there is no data associated with the given key.
     * @throws IllegalArgumentException If the provided type does not match the type of the stored data.
     */
    <T> T getData(String key, Class<T> type);

    /**
     * Retrieves the value associated with the given key from the context.
     *
     * @param <T>  The expected type of the value.
     * @param key  The unique key that identifies the data in the context. Must not be {@code null}.
     * @param type The {@link Class} type of the data. Must not be {@code null}.
     * @return An {@link Optional} containing the data if it exists and can be converted to the given type,
     * or an empty {@link Optional}.
     * @throws IllegalArgumentException If the provided type does not match the type of the stored data.
     */
    <T> Optional<T> findData(String key, Class<T> type);
}
