package com.github.syr0ws.craftventory.internal.config.loader.model.pagination;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.DynamicPaginationConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationNavItemConfig;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;

public class DynamicPaginationConfigLoader extends PaginationConfigLoader {

    public DynamicPaginationConfigLoader(ConfigLoaderManager manager) {
        super(manager);
    }

    @Override
    public PaginationConfig load(ConfigurationSection section) throws InventoryConfigException {
        Validate.notNull(section, "section cannot be null");

        String paginationId = super.loadPaginationId(section);
        char symbol = super.loadSymbol(section);

        PaginationNavItemConfig previousPageItem = super.loadPaginationNavItem(section, PROPERTY_PREVIOUS_PAGE_ITEM);
        PaginationNavItemConfig nextPageItem = super.loadPaginationNavItem(section, PROPERTY_NEXT_PAGE_ITEM);

        InventoryItemConfig paginationItem = this.loadPaginationItem(section);

        return new DynamicPaginationConfig(paginationId, previousPageItem, nextPageItem, symbol, paginationItem);
    }

    @Override
    public String getName() {
        return ConfigLoaderRegistry.DYNAMIC_PAGINATION;
    }

    private InventoryItemConfig loadPaginationItem(ConfigurationSection section) throws InventoryConfigException {

        ConfigurationSection paginationItemSection = section.getConfigurationSection(PROPERTY_PAGINATION_ITEM);

        if(paginationItemSection == null) {
            throw new InventoryConfigException("Section '%s.%s' not found".formatted(section.getCurrentPath(), PROPERTY_PAGINATION_ITEM));
        }

        ConfigLoader<InventoryItemConfig> loader = super.getManager().getLoader(ConfigLoaderRegistry.INVENTORY_ITEM, InventoryItemConfig.class);
        return loader.load(paginationItemSection);
    }
}
