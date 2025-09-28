package com.github.syr0ws.craftventory.internal.config.loader.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;
import com.github.syr0ws.craftventory.common.inventory.action.BackwardAction;

import java.util.Set;

public class BackActionLoader extends YamlCommonActionLoader {

    private static final String INVENTORY_ID_KEY = "inventory-id";

    @Override
    public ClickAction load(ConfigurationMap map) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(map);
        String inventoryId = map.getString(INVENTORY_ID_KEY, null);

        return new BackwardAction(clickTypes, inventoryId);
    }

    @Override
    public String getName() {
        return BackwardAction.ACTION_NAME;
    }
}
