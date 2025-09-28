package com.github.syr0ws.craftventory.internal.config.loader.model;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftstack.CraftStack;
import com.github.syr0ws.craftstack.item.Item;
import com.github.syr0ws.craftstack.loader.ItemLoaderService;
import com.github.syr0ws.craftstack.util.config.ConfigurationException;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;

public class ItemLoader implements ConfigLoader<Item> {

    private static final ItemLoaderService<ConfigurationSection> ITEM_LOADER_SERVICE = CraftStack.getDefaultItemLoader();

    @Override
    public Item load(ConfigurationSection section) throws InventoryConfigException {
        Validate.notNull(section, "section cannot be null");
        try {
            return ITEM_LOADER_SERVICE.load(section);
        } catch (ConfigurationException exception) {
            throw new InventoryConfigException("An error occurred while loading the inventory item at '%s'".formatted(section.getCurrentPath()), exception);
        }
    }

    @Override
    public Class<Item> getType() {
        return Item.class;
    }

    @Override
    public String getName() {
        return ConfigLoaderRegistry.ITEM;
    }
}
