package com.github.syr0ws.craftventory.internal.config.loader.model;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftstack.item.Item;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemActionsConfig;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemConfig;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;

public class InventoryItemConfigLoader extends AbstractInventoryItemLoader<InventoryItemConfig> {

    public InventoryItemConfigLoader(ConfigLoaderManager manager) {
        super(manager);
    }

    @Override
    public InventoryItemConfig load(ConfigurationSection section) throws InventoryConfigException {
        Validate.notNull(section, "section cannot be null");

        String itemId = super.loadItemId(section);
        Item item = super.loadItem(section);
        InventoryItemActionsConfig actions = super.loadActions(section);

        return new InventoryItemConfig(itemId, item, actions);
    }

    @Override
    public Class<InventoryItemConfig> getType() {
        return InventoryItemConfig.class;
    }

    @Override
    public String getName() {
        return ConfigLoaderRegistry.INVENTORY_ITEM;
    }
}
