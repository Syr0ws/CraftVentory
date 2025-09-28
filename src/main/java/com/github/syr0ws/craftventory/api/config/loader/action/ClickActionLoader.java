package com.github.syr0ws.craftventory.api.config.loader.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;

/**
 * Responsible for loading a specific {@link ClickAction} instance from a given data source.
 */
public interface ClickActionLoader {

    /**
     * Loads a {@link ClickAction} from the provided configuration map.
     *
     * @param map The configuration map containing the data to load the action from. Must not be {@code null}.
     * @return The loaded {@link ClickAction} instance.
     * @throws InventoryConfigException If there is an error during the loading process.
     */
    ClickAction load(ConfigurationMap map) throws InventoryConfigException;

    /**
     * Retrieves the name of this loader.
     *
     * <p>
     * The name if used to uniquely identify the loader. It will be used to find the right loader
     * to load a specific action.
     * <br/>
     * To avoid confusion, the name of the loader should be the same as the name of the action it loads.
     * </p>
     *
     * @return The name of the loader. Must not be {@code null}.
     */
    String getName();
}
