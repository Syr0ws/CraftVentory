package com.github.syr0ws.craftventory.api.config.loader.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;

public interface ClickActionLoader {

    ClickAction load(ConfigurationMap config) throws InventoryConfigException;

    String getActionName();
}
