package com.github.syr0ws.craftventory.internal.config.loader.model;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftstack.item.Item;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemActionsConfig;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;

public abstract class AbstractInventoryItemLoader<T> implements ConfigLoader<T> {

    private static final String PROPERTY_ITEM = "item";

    private final ConfigLoaderManager manager;

    public AbstractInventoryItemLoader(ConfigLoaderManager manager) {
        Validate.notNull(manager, "manager cannot be null");
        this.manager = manager;
    }

    protected String loadItemId(ConfigurationSection section) {
        return section.getName();
    }

    protected Item loadItem(ConfigurationSection section) throws InventoryConfigException {

        ConfigurationSection itemSection = section.getConfigurationSection(PROPERTY_ITEM);

        if(itemSection == null) {
            throw new InventoryConfigException("Section '%s.%s' not found".formatted(section.getCurrentPath(), PROPERTY_ITEM));
        }

        ConfigLoader<Item> loader = this.manager.getLoader(ConfigLoaderRegistry.ITEM, Item.class);
        return loader.load(itemSection);
    }

    protected InventoryItemActionsConfig loadActions(ConfigurationSection section) throws InventoryConfigException {
        ConfigLoader<InventoryItemActionsConfig> loader = this.manager.getLoader(ConfigLoaderRegistry.INVENTORY_ITEM_ACTIONS, InventoryItemActionsConfig.class);
        return loader.load(section);
    }

    protected ConfigLoaderManager getManager() {
        return this.manager;
    }
}
