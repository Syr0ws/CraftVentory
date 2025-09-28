package com.github.syr0ws.craftventory.internal.config.dao;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.api.config.model.InventoryConfig;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YamlInventoryConfigDAO implements InventoryConfigDAO {

    private final Plugin plugin;
    private final ConfigLoaderManager configLoaderManager;

    public YamlInventoryConfigDAO(Plugin plugin, ConfigLoaderManager configLoaderManager) {
        Validate.notNull(plugin, "plugin cannot be null");
        Validate.notNull(configLoaderManager, "configLoaderManager cannot be null");

        this.plugin = plugin;
        this.configLoaderManager = configLoaderManager;
    }

    @Override
    public InventoryConfig loadConfig(Path file) throws InventoryConfigException {
        Validate.notNull(file, "file cannot be null");

        if (!Files.exists(file)) {
            throw new InventoryConfigException(String.format("Inventory configuration file '%s' does not exist", file));
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file.toFile());

        return this.loadConfig(config);
    }

    @Override
    public Set<InventoryConfig> loadConfigs(Path folder) throws InventoryConfigException {
        Validate.notNull(folder, "folder cannot be null");

        if (!Files.isDirectory(folder)) {
            throw new InventoryConfigException(String.format("Path '%s' is not a directory or does not exist", folder.toAbsolutePath()));
        }

        Set<InventoryConfig> configs;

        try (Stream<Path> files = Files.list(folder)) {

            configs = files.map(file -> {
                try {
                    return this.loadConfig(file);
                } catch (InventoryConfigException exception) {
                    String message = "An error occurred while loading the inventory configuration file '%s'".formatted(file.toAbsolutePath());
                    this.plugin.getLogger().log(Level.SEVERE, message, exception);
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toSet());

        } catch (IOException exception) {
            throw new InventoryConfigException(String.format("An error occurred while listing configuration files of folder '%s'", folder.toAbsolutePath()), exception);
        }

        return configs;
    }

    private InventoryConfig loadConfig(ConfigurationSection section) throws InventoryConfigException {
        ConfigLoader<InventoryConfig> loader = this.configLoaderManager.getLoader(ConfigLoaderRegistry.INVENTORY, InventoryConfig.class);
        return loader.load(section);
    }
}
