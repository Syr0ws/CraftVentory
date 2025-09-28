package com.github.syr0ws.craftventory.internal.config.loader.model;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.loader.action.ClickActionLoader;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.api.config.loader.action.ClickActionLoaderManager;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemActionsConfig;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class InventoryItemsActionsConfigLoader implements ConfigLoader<InventoryItemActionsConfig> {

    private static final String PROPERTY_ACTIONS = "actions";
    private static final String PROPERTY_ACTION_NAME = "action";

    private final ClickActionLoaderManager manager;

    public InventoryItemsActionsConfigLoader(ClickActionLoaderManager manager) {
        Validate.notNull(manager, "manager cannot be null");
        this.manager = manager;
    }

    @Override
    public InventoryItemActionsConfig load(ConfigurationSection section) throws InventoryConfigException {

        List<Map<?,?>> actionPropertiesMapList = section.getMapList(PROPERTY_ACTIONS);
        List<ClickAction> actions = new ArrayList<>();

        for(Map<?,?> actionProperties : actionPropertiesMapList) {

            ConfigurationMap config = new ConfigurationMap(section, actionProperties);

            if(!config.isString(PROPERTY_ACTION_NAME)) {
                throw new InventoryConfigException("Property '%s' not found in an action at '%s'".formatted(PROPERTY_ACTION_NAME, section.getCurrentPath()));
            }

            String actionName = config.getString(PROPERTY_ACTION_NAME);

            ClickActionLoader loader = this.manager.getLoader(actionName)
                    .orElseThrow(() -> new InventoryConfigException("No loader found for action '%s'".formatted(actionName)));

            ClickAction action = loader.load(config);
            actions.add(action);
        }

        return new InventoryItemActionsConfig(actions);
    }

    @Override
    public Class<InventoryItemActionsConfig> getType() {
        return InventoryItemActionsConfig.class;
    }

    @Override
    public String getName() {
        return ConfigLoaderRegistry.INVENTORY_ITEM_ACTIONS;
    }
}
