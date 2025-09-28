package com.github.syr0ws.craftventory.internal.config.loader.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;
import com.github.syr0ws.craftventory.common.inventory.action.UpdateContentAction;

import java.util.Set;

public class UpdateContentActionLoader extends YamlCommonActionLoader {

    @Override
    public ClickAction load(ConfigurationMap map) throws InventoryConfigException {
        Set<ClickType> clickTypes = super.loadClickTypes(map);
        return new UpdateContentAction(clickTypes);
    }

    @Override
    public String getName() {
        return UpdateContentAction.ACTION_NAME;
    }
}
