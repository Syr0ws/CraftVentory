package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;
import com.github.syr0ws.craftventory.common.inventory.action.UpdatePaginationAction;

import java.util.HashSet;
import java.util.Set;

public class YamlUpdatePaginationActionLoader extends YamlCommonActionLoader {

    private static final String PAGINATION_IDS_KEY = "pagination-ids";

    @Override
    public ClickAction load(ConfigurationMap map) throws InventoryConfigException {

        if (!map.isList(PAGINATION_IDS_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' not found or is not a list for action '%s' at '%s'", PAGINATION_IDS_KEY, this.getName(), map.getCurrentPath()));
        }

        Set<ClickType> clickTypes = super.loadClickTypes(map);
        Set<String> paginationIds = new HashSet<>(map.getStringList(PAGINATION_IDS_KEY));

        return new UpdatePaginationAction(clickTypes, paginationIds);
    }

    @Override
    public String getName() {
        return UpdatePaginationAction.ACTION_NAME;
    }
}
