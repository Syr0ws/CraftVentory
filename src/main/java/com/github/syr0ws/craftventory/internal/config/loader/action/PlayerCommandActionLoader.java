package com.github.syr0ws.craftventory.internal.config.loader.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.inventory.action.PlayerCommandAction;

import java.util.List;
import java.util.Set;

public class PlayerCommandActionLoader extends CommandActionLoader {

    @Override
    public ClickAction load(ConfigurationMap map) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(map);
        List<String> commands = super.loadCommands(map);

        return new PlayerCommandAction(clickTypes, commands);
    }

    @Override
    public String getName() {
        return PlayerCommandAction.ACTION_NAME;
    }
}
