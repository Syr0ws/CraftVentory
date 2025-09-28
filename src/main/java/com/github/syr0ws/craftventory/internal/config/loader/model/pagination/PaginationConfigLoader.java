package com.github.syr0ws.craftventory.internal.config.loader.model.pagination;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationNavItemConfig;
import com.github.syr0ws.craftventory.api.inventory.pagination.PaginationType;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;

public class PaginationConfigLoader implements ConfigLoader<PaginationConfig> {

    protected static final String PROPERTY_PAGINATED_ITEMS = "paginated-items";
    protected static final String PROPERTY_PAGINATION_ITEM = "pagination-item";
    protected static final String PROPERTY_PREVIOUS_PAGE_ITEM = "previous-page-item";
    protected static final String PROPERTY_NEXT_PAGE_ITEM = "next-page-item";

    private final ConfigLoaderManager manager;

    public PaginationConfigLoader(ConfigLoaderManager manager) {
        Validate.notNull(manager, "manager cannot be null");
        this.manager = manager;
    }

    @Override
    public PaginationConfig load(ConfigurationSection section) throws InventoryConfigException {
        Validate.notNull(section, "section cannot be null");

        PaginationType type = this.loadPaginationType(section);

        return switch (type) {
            case STATIC -> this.manager.getLoader(ConfigLoaderRegistry.STATIC_PAGINATION, PaginationConfig.class).load(section);
            case DYNAMIC -> this.manager.getLoader(ConfigLoaderRegistry.DYNAMIC_PAGINATION, PaginationConfig.class).load(section);
        };
    }

    @Override
    public Class<PaginationConfig> getType() {
        return PaginationConfig.class;
    }

    @Override
    public String getName() {
        return ConfigLoaderRegistry.PAGINATION;
    }

    private PaginationType loadPaginationType(ConfigurationSection section) throws InventoryConfigException {

        if(section.isConfigurationSection(PROPERTY_PAGINATED_ITEMS)) {
            return PaginationType.STATIC;
        }

        if(section.isConfigurationSection(PROPERTY_PAGINATION_ITEM)) {
            return PaginationType.DYNAMIC;
        }

        throw new InventoryConfigException("Could not determine pagination type for pagination at '%s'".formatted(section.getCurrentPath()));
    }

    protected String loadPaginationId(ConfigurationSection section) {
        return section.getName();
    }

    protected PaginationNavItemConfig loadPaginationNavItem(ConfigurationSection section, String key) throws InventoryConfigException {

        ConfigurationSection navigationSection = section.getConfigurationSection(key);

        if(navigationSection == null) {
            throw new InventoryConfigException("Section '%s.%s' not found".formatted(section.getCurrentPath(), key));
        }

        ConfigLoader<PaginationNavItemConfig> loader = this.manager.getLoader(ConfigLoaderRegistry.PAGINATION_NAV_ITEM, PaginationNavItemConfig.class);
        return loader.load(navigationSection);
    }

    protected char loadSymbol(ConfigurationSection section) throws InventoryConfigException {
        ConfigLoader<Character> loader = this.manager.getLoader(ConfigLoaderRegistry.SYMBOL, Character.class);
        return loader.load(section);
    }

    protected ConfigLoaderManager getManager() {
        return this.manager;
    }
}
