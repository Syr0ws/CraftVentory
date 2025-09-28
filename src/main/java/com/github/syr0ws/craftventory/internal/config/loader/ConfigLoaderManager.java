package com.github.syr0ws.craftventory.internal.config.loader;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.api.config.loader.action.ClickActionLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.model.*;
import com.github.syr0ws.craftventory.internal.config.loader.model.pagination.DynamicPaginationConfigLoader;
import com.github.syr0ws.craftventory.internal.config.loader.model.pagination.PaginationConfigLoader;
import com.github.syr0ws.craftventory.internal.config.loader.model.pagination.PaginationNavItemConfigLoader;
import com.github.syr0ws.craftventory.internal.config.loader.model.pagination.StaticPaginationConfigLoader;

import java.util.HashMap;
import java.util.Map;

import static com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry.*;

public class ConfigLoaderManager {

    private final Map<String, ConfigLoader<?>> loaders = new HashMap<>();

    public ConfigLoaderManager(ClickActionLoaderManager clickActionLoaderManager) {
        Validate.notNull(clickActionLoaderManager, "clickActionLoaderManager cannot be null");
        this.registerDefaultLoaders(clickActionLoaderManager);
    }

    private void registerDefaultLoaders(ClickActionLoaderManager manager) {
        this.loaders.put(INVENTORY, new InventoryConfigLoader(this));
        this.loaders.put(INVENTORY_ITEM, new InventoryItemConfigLoader(this));
        this.loaders.put(INVENTORY_CONTENT_ITEM, new InventoryContentItemConfigLoader(this));
        this.loaders.put(INVENTORY_ITEM_ACTIONS, new InventoryItemsActionsConfigLoader(manager));
        this.loaders.put(PAGINATION, new PaginationConfigLoader(this));
        this.loaders.put(STATIC_PAGINATION, new StaticPaginationConfigLoader(this));
        this.loaders.put(DYNAMIC_PAGINATION, new DynamicPaginationConfigLoader(this));
        this.loaders.put(PAGINATION_NAV_ITEM, new PaginationNavItemConfigLoader(this));
        this.loaders.put(ITEM, new ItemLoader());
        this.loaders.put(SYMBOL, new SymbolLoader());
    }

    @SuppressWarnings("unchecked")
    public <T> ConfigLoader<T> getLoader(String name, Class<T> type) {
        Validate.notEmpty(name, "name cannot be null or empty");
        Validate.notNull(type, "type cannot be null");

        ConfigLoader<?> loader = this.loaders.get(name);

        if(loader == null) {
            throw new NullPointerException("No loader found with name '%s'".formatted(name));
        }

        Class<?> loaderType = loader.getType();

        if(!loaderType.equals(type)) {
            throw new IllegalArgumentException("Incompatible loader type '%s' and provided type '%s'".formatted(loaderType.getName(), type.getName()));
        }

        return (ConfigLoader<T>) loader;
    }
}
