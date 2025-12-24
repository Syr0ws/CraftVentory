package com.github.syr0ws.craftventory.api.config.dao;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.model.InventoryConfig;

import java.nio.file.Path;
import java.util.Set;

/**
 * Data Access Object (DAO) interface for loading inventory configurations.
 */
public interface InventoryConfigDAO {

    /**
     * Loads an {@link InventoryConfig} from a specified file.
     *
     * @param file the path to the configuration file. Must not be {@code null}
     * @return the loaded {@link InventoryConfig}
     * @throws IllegalArgumentException if file is {@code null}
     * @throws InventoryConfigException if an error occurs while loading or parsing the configuration file
     */
    InventoryConfig loadConfig(Path file) throws InventoryConfigException;

    /**
     * Loads all {@link InventoryConfig} instances from a specified folder.
     * <p>
     * This method scans the folder for valid configuration files, attempts to load them,
     * and returns a set of all successfully parsed {@link InventoryConfig} instances.
     * <br>
     * If an error occurs while loading a specific configuration file, that file will be skipped,
     * and a stack trace will be generated.
     * </p>
     *
     * @param folder the path to the folder containing configuration files. Must not be {@code null}.
     * @return a set of successfully loaded {@link InventoryConfig} instances. If no valid configuration files
     *         are found, the set will be empty
     * @throws IllegalArgumentException if the {@code folder} parameter is {@code null}
     * @throws InventoryConfigException if the given path does not point to a folder or if an error occurs
     *                                   while accessing the folder
     */
    Set<InventoryConfig> loadConfigs(Path folder) throws InventoryConfigException;
}
