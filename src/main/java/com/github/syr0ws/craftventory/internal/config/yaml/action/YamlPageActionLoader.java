package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;
import org.bukkit.configuration.ConfigurationSection;

public abstract class YamlPageActionLoader extends YamlCommonActionLoader {

    private static final String PAGINATION_ID_KEY = "pagination-id";

    protected String getPaginationId(ConfigurationSection section) throws InventoryConfigException {

        if (!section.isString(PAGINATION_ID_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' missing at '%s'", PAGINATION_ID_KEY, section.getCurrentPath()));
        }

        return section.getString(PAGINATION_ID_KEY);
    }
}
