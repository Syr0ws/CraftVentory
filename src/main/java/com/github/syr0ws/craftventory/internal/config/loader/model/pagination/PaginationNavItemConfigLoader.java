package com.github.syr0ws.craftventory.internal.config.loader.model.pagination;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationNavItemConfig;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;

public class PaginationNavItemConfigLoader implements ConfigLoader<PaginationNavItemConfig> {

    private static final String PROPERTY_NAVIGATION_ITEM = "navigation-item";
    private static final String PROPERTY_NO_NAVIGATION_ITEM = "no-navigation-item";

    private final ConfigLoaderManager manager;

    public PaginationNavItemConfigLoader(ConfigLoaderManager manager) {
        Validate.notNull(manager, "manager cannot be null");
        this.manager = manager;
    }

    @Override
    public PaginationNavItemConfig load(ConfigurationSection section) throws InventoryConfigException {
        Validate.notNull(section, "section cannot be null");

        InventoryItemConfig navigationItem = this.loadItem(section, PROPERTY_NAVIGATION_ITEM);

        InventoryItemConfig noNavigationItem = section.isConfigurationSection(PROPERTY_NO_NAVIGATION_ITEM) ?
                this.loadItem(section, PROPERTY_NO_NAVIGATION_ITEM) : null;

        char symbol = this.loadSymbol(section);

        return new PaginationNavItemConfig(navigationItem, noNavigationItem, symbol);
    }

    @Override
    public Class<PaginationNavItemConfig> getType() {
        return PaginationNavItemConfig.class;
    }

    @Override
    public String getName() {
        return ConfigLoaderRegistry.PAGINATION_NAV_ITEM;
    }

    private InventoryItemConfig loadItem(ConfigurationSection section, String key) throws InventoryConfigException {

        ConfigurationSection itemSection = section.getConfigurationSection(key);

        if(itemSection == null) {
            throw new InventoryConfigException("Section '%s.%s' not found".formatted(section.getCurrentPath(), key));
        }

        ConfigLoader<InventoryItemConfig> loader = this.manager.getLoader(ConfigLoaderRegistry.INVENTORY_ITEM, InventoryItemConfig.class);
        return loader.load(itemSection);
    }

    private char loadSymbol(ConfigurationSection section) throws InventoryConfigException {
        ConfigLoader<Character> loader = this.manager.getLoader(ConfigLoaderRegistry.SYMBOL, Character.class);
        return loader.load(section);
    }
}
