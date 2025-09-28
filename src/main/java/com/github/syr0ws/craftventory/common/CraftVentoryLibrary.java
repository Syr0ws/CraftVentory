package com.github.syr0ws.craftventory.common;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.InventoryService;
import com.github.syr0ws.craftventory.api.config.dao.InventoryConfigDAO;
import com.github.syr0ws.craftventory.api.config.loader.action.ClickActionLoaderManager;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.action.SimpleClickActionLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.action.ActionLoaderEnum;
import com.github.syr0ws.craftventory.internal.util.SimpleContext;
import com.github.syr0ws.craftventory.internal.SimpleInventoryService;
import com.github.syr0ws.craftventory.internal.config.dao.YamlInventoryConfigDAO;
import com.github.syr0ws.craftventory.internal.inventory.listener.CraftVentoryListener;
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
     * Creates and configures a default {@link InventoryConfigDAO} for loading inventory configurations
     * from YAML files.
     *
     * @param plugin The plugin instance used for file operations and logging.
     * @param clickActionLoaderManager The manager responsible for loading click actions.
     * @return A fully configured {@link InventoryConfigDAO} for loading inventory configurations from YAML files.
     */
    public static InventoryConfigDAO createDefaultConfigDAO(Plugin plugin, ClickActionLoaderManager clickActionLoaderManager) {
        Validate.notNull(plugin, "plugin cannot be null");
        Validate.notNull(clickActionLoaderManager, "clickActionLoaderManager cannot be null");

        ConfigLoaderManager configLoaderManager = new ConfigLoaderManager(clickActionLoaderManager);

        return new YamlInventoryConfigDAO(plugin, configLoaderManager);
    }

    /**
     * Creates and configures a default {@link ClickActionLoaderManager} with built-in loaders for various click actions.
     *
     * @return A fully configured {@link ClickActionLoaderManager} with default action loaders.
     */
    public static ClickActionLoaderManager createDefaultClickActionLoaderManager() {

        ClickActionLoaderManager manager = new SimpleClickActionLoaderManager();

        for(ActionLoaderEnum action : ActionLoaderEnum.values()) {
            manager.addLoader(action.getLoader());
        }

        return manager;
    }

    /**
     * Creates an empty {@link Context}.
     * @return An instance of {@link Context}.
     */
    public static Context createContext() {
        return new SimpleContext();
    }
}
