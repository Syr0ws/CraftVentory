package com.github.syr0ws.craftventory.api.inventory.data;

import java.util.Optional;
import java.util.Set;

/**
 * Represents a container used to store data. Each data is associated with a unique
 * key and its Java class type.
 */
public interface DataStore {

    /**
     * Adds a new data represented as a key-value pair to the store.
     *
     * @param key   The unique key that identifies the data entry. Must not be {@code null} or empty.
     * @param type  The {@link Class} type of the data. Must not be {@code null}.
     * @param value The value to store.
     * @param <T>   The type of the value to store.
     */
    <T> void setData(String key, Class<T> type, T value);

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
     * Checks if the store contains a data associated with the given key.
     *
     * @param key The unique key that identifies the data entry. Must not be {@code null}.
     * @return {@code true} if a data entry exists for the given key, {@code false} otherwise.
     */
    boolean hasData(String key);

    /**
     * Checks if the store contains a data of the given type associated with the given key.
     *
     * @param key The unique key that identifies the data entry. Must not be {@code null}.
     * @param type The {@link Class} type of the data. Must not be {@code null}.
     * @return {@code true} if a data entry exists for the given key, {@code false} otherwise.
     */
    boolean hasData(String key, Class<?> type);

    /**
     * Retrieves a data from the store by key.
     *
     * @param key  The unique key that identifies the data entry. Must not be {@code null}.
     * @param type The {@link Class} type of the data. Must not be {@code null}.
     * @param <T>  The expected type of the data.
     * @return An {@link Optional} containing the data entry if it exists, or an empty {@link Optional}
     * if not found.
     * @throws IllegalArgumentException If a data entry has been found but its type does not match the given type.
     */
    <T> Optional<T> getData(String key, Class<T> type);

    /**
     * Retrieves all the currently stored keys.
     *
     * @return An immutable {@link Set} containing all the keys of the stored data entries.
     * Never {@code null}.
     */
    Set<String> getKeys();
}
