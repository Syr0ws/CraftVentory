package com.github.syr0ws.craftventory.internal.config.loader.model;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftstack.item.Item;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.api.config.model.InventoryContentItemConfig;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemActionsConfig;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;

public class InventoryContentItemConfigLoader extends AbstractInventoryItemLoader<InventoryContentItemConfig> {

    public InventoryContentItemConfigLoader(ConfigLoaderManager manager) {
        super(manager);
    }

    @Override
    public InventoryContentItemConfig load(ConfigurationSection section) throws InventoryConfigException {
        Validate.notNull(section, "section cannot be null");

        String itemId = super.loadItemId(section);
        Item item = super.loadItem(section);
        InventoryItemActionsConfig actions = super.loadActions(section);
        char symbol = this.loadSymbol(section);

        return new InventoryContentItemConfig(itemId, item, actions, symbol);
    }

    @Override
    public Class<InventoryContentItemConfig> getType() {
        return InventoryContentItemConfig.class;
    }

    @Override
    public String getName() {
        return ConfigLoaderRegistry.INVENTORY_CONTENT_ITEM;
    }

    private char loadSymbol(ConfigurationSection section) throws InventoryConfigException {
        ConfigLoader<Character> loader = super.getManager().getLoader(ConfigLoaderRegistry.SYMBOL, Character.class);
        return loader.load(section);
    }
}
