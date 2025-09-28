package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;
import com.github.syr0ws.craftventory.common.inventory.action.OpenInventoryAction;

import java.util.Set;

public class YamlOpenInventoryActionLoader extends YamlCommonActionLoader {

    private static final String INVENTORY_ID_KEY = "inventory-id";
    private static final String NEW_HISTORY = "new-history";

    @Override
    public ClickAction load(ConfigurationMap map) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(map);

        if (!map.isString(INVENTORY_ID_KEY)) {
            throw new InventoryConfigException("Property '%s' not found or is not a string for action '%s' at '%s'".formatted(INVENTORY_ID_KEY, this.getName(), map.getCurrentPath()));
        }

        String inventoryId = map.getString(INVENTORY_ID_KEY);

        if (inventoryId.isEmpty()) {
            throw new InventoryConfigException("Property '%s' cannot be empty for action '%s' at '%s'".formatted(INVENTORY_ID_KEY, this.getName(), map.getCurrentPath()));
        }

        boolean newHistory = map.getBoolean(NEW_HISTORY, true);

        return new OpenInventoryAction(clickTypes, inventoryId, newHistory);
    }

    @Override
    public String getName() {
        return OpenInventoryAction.ACTION_NAME;
    }
}
