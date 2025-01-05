package com.github.syr0ws.craftventory.api.inventory.data;

import java.util.Optional;
import java.util.Set;

/**
 * Represents a container used to store data in the context of an inventory. Each data is stored
 * by key, and type safety is ensured by class type comparison.
 */
public interface DataStore {

    /**
     * Adds a new data represented as a key-value pair to the store.
     *
     * @param <T>   The type of the value to store.
     * @param key   The unique key that identifies the data in the store. Must not be {@code null} or empty.
     * @param value The value to store.
     * @param type  The {@link Class} type of the data. Must not be {@code null}.
     */
    <T> void setData(String key, T value, Class<T> type);

    /**
     * Removes a data from the store.
     *
     * @param key The unique key that identifies the data entry to remove. Must not be {@code null}.
     * @return {@code true} if the data entry was successfully removed, {@code false} otherwise.
     */
    boolean removeData(String key);

    /**
     * Removes all the stored data from the store.
     */
    void clear();

    /**
     * Checks whether the context contains a data associated with the given key.
     *
     * @param key The unique key that identifies the data in the store. Must not be {@code null}.
     * @return {@code true} if there is a value associated with the given key, otherwise {@code false}.
     */
    boolean hasData(String key);

    /**
     * Checks whether the context contains a data of the given type associated with the given key.
     *
     * @param key The unique key that identifies the data in the store. Must not be {@code null}.
     * @param type The {@link Class} type of the data. Must not be {@code null}.
     * @return {@code true} if there is a value associated with the given key and that can be converted
     * to the given type, otherwise {@code false}.
     */
    boolean hasData(String key, Class<?> type);

    /**
     * Retrieves the value associated with the given key from the store.
     *
     * @param <T>  The expected type of the data.
     * @param key  The unique key that identifies the data in the store. Must not be {@code null}.
     * @param type The {@link Class} type of the data. Must not be {@code null}.
     * @return An {@link Optional} containing the data entry if it exists, or an empty {@link Optional}
     * if not found.
     * @throws IllegalArgumentException If the provided type does not match the type of the stored data.
     */
    <T> Optional<T> getData(String key, Class<T> type);

    /**
     * Retrieves all the currently stored keys.
     *
     * @return An immutable {@link Set} containing all the stored keys. Never {@code null}.
     */
    Set<String> getKeys();
}
