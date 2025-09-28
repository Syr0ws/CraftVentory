package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.inventory.action.NextPageAction;

import java.util.Set;

public class YamlNextPageActionLoader extends YamlPageActionLoader {

    @Override
    public ClickAction load(ConfigurationMap map) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(map);
        String paginationId = super.getPaginationId(map);

        return new NextPageAction(clickTypes, paginationId);
    }

    @Override
    public String getName() {
        return NextPageAction.ACTION_NAME;
    }
}
