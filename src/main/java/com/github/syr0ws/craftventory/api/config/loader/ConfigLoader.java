package com.github.syr0ws.craftventory.api.config.loader;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Represents a loader for configuration data of type {@code T}.
 *
 * @param <T> the type of configuration data to be loaded
 */
public interface ConfigLoader<T> {

    /**
     * Loads configuration data from the specified {@link ConfigurationSection}.
     *
     * @param section the configuration section to load data from
     * @return the loaded configuration data
     * @throws InventoryConfigException if an error occurs during loading
     */
    T load(ConfigurationSection section) throws InventoryConfigException;

    /**
     * Retrieves the class type of the configuration data.
     *
     * @return the class type of the configuration data
     */
    Class<T> getType();

    /**
     * Retrieves the name of the loader.
     *
     * @return the name of the loader
     */
    String getName();
}
