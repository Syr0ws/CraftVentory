package com.github.syr0ws.craftventory.internal.config.loader.model.pagination;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationNavItemConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.StaticPaginationConfig;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class StaticPaginationConfigLoader extends PaginationConfigLoader {

    public StaticPaginationConfigLoader(ConfigLoaderManager manager) {
        super(manager);
    }

    @Override
    public PaginationConfig load(ConfigurationSection section) throws InventoryConfigException {
        Validate.notNull(section, "section cannot be null");

        String paginationId = super.loadPaginationId(section);
        char symbol = super.loadSymbol(section);

        PaginationNavItemConfig previousPageItem = super.loadPaginationNavItem(section, PROPERTY_PREVIOUS_PAGE_ITEM);
        PaginationNavItemConfig nextPageItem = super.loadPaginationNavItem(section, PROPERTY_NEXT_PAGE_ITEM);

        List<InventoryItemConfig> paginatedItems = this.loadPaginatedItems(section);

        return new StaticPaginationConfig(paginationId, previousPageItem, nextPageItem, symbol, paginatedItems);
    }

    @Override
    public String getName() {
        return ConfigLoaderRegistry.STATIC_PAGINATION;
    }

    private List<InventoryItemConfig> loadPaginatedItems(ConfigurationSection section) throws InventoryConfigException {

        ConfigurationSection paginatedItemsSection = section.getConfigurationSection(PROPERTY_PAGINATED_ITEMS);

        if(paginatedItemsSection == null) {
            throw new InventoryConfigException("Section '%s.%s' not found".formatted(section.getCurrentPath(), PROPERTY_PAGINATED_ITEMS));
        }

        List<InventoryItemConfig> paginatedItems = new ArrayList<>();

        for(String key : section.getKeys(false)) {

            ConfigurationSection paginatedItemSection = paginatedItemsSection.getConfigurationSection(key);

            if(paginatedItemSection == null) {
                throw new InventoryConfigException("Section '%s.%s' must be a section".formatted(paginatedItemsSection.getCurrentPath(), key));
            }

            ConfigLoader<InventoryItemConfig> loader = super.getManager().getLoader(ConfigLoaderRegistry.INVENTORY_ITEM, InventoryItemConfig.class);
            InventoryItemConfig paginatedItem = loader.load(paginatedItemSection);

            paginatedItems.add(paginatedItem);
        }

        return paginatedItems;
    }
}
