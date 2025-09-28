package com.github.syr0ws.craftventory.internal.config.loader.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;

public abstract class PageActionLoader extends YamlCommonActionLoader {

    private static final String PAGINATION_ID_KEY = "pagination-id";

    protected String getPaginationId(ConfigurationMap map) throws InventoryConfigException {

        if (!map.isString(PAGINATION_ID_KEY)) {
            throw new InventoryConfigException("Property '%s' not found or is not a string for action '%s' at '%s'".formatted(PAGINATION_ID_KEY, this.getName(), map.getCurrentPath()));
        }

        return map.getString(PAGINATION_ID_KEY);
    }
}
