package com.github.syr0ws.craftventory.common;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.InventoryService;
import com.github.syr0ws.craftventory.api.config.action.ClickActionLoaderFactory;
import com.github.syr0ws.craftventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.internal.util.SimpleContext;
import com.github.syr0ws.craftventory.internal.SimpleInventoryService;
import com.github.syr0ws.craftventory.internal.config.yaml.YamlInventoryConfigDAO;
import com.github.syr0ws.craftventory.internal.config.yaml.action.*;
import com.github.syr0ws.craftventory.internal.inventory.listener.CraftVentoryListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * A utility class for creating and configuring the key components of the library.
 */
public class CraftVentoryLibrary {

    /**
     * Creates and configures an instance of the {@link InventoryService} for managing inventories.
     * <p>
     * This method also registers a listener to handle events related to inventories.
     * </p>
     *
     * @param plugin The plugin instance that will be used to register events.
     * @return An instance of the {@link InventoryService} to manage inventories.
     */
    public static InventoryService createInventoryService(Plugin plugin) {

        SimpleInventoryService service = new SimpleInventoryService(plugin);
        CraftVentoryListener listener = new CraftVentoryListener(plugin, service);

        PluginManager manager = plugin.getServer().getPluginManager();
        manager.registerEvents(listener, plugin);

        return service;
    }

    /**
     * Creates the default {@link InventoryConfigDAO} which is responsible for loading inventory
     * configurations from YAML files.
     *
     * @param factory The ClickActionLoaderFactory used for loading click actions.
     * @return An instance of {@link InventoryConfigDAO} for loading inventory configurations.
     */
    public static InventoryConfigDAO createDefaultConfigDAO(ClickActionLoaderFactory<ConfigurationSection> factory) {
        return new YamlInventoryConfigDAO(factory);
    }

    /**
     * Creates and configures a default {@link ClickActionLoaderFactory} for loading click actions
     * from YAML configuration files.
     *
     * @return A fully configured {@link ClickActionLoaderFactory} for loading click actions from YAML files.
     */
    public static ClickActionLoaderFactory<ConfigurationMap> createDefaultClickActionLoaderFactory() {

        ClickActionLoaderFactory<ConfigurationMap> factory = new YamlClickActionLoaderFactory();

        for(YamlActionLoaderEnum action : YamlActionLoaderEnum.values()) {
            factory.addLoader(action.getLoader());
        }

        return factory;
    }

    /**
     * Creates an empty {@link Context}.
     * @return An instance of {@link Context}.
     */
    public static Context createContext() {
        return new SimpleContext();
    }
}
