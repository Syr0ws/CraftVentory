package com.github.syr0ws.craftventory.internal.config.loader.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.inventory.action.ConsoleCommandAction;

import java.util.List;
import java.util.Set;

public class ConsoleCommandActionLoader extends CommandActionLoader {

    @Override
    public ClickAction load(ConfigurationMap map) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(map);
        List<String> commands = super.loadCommands(map);

        return new ConsoleCommandAction(clickTypes, commands);
    }

    @Override
    public String getName() {
        return ConsoleCommandAction.ACTION_NAME;
    }
}