package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;

import java.util.List;

public abstract class YamlCommandActionLoader extends YamlCommonActionLoader {

    private static final String COMMANDS_KEY = "commands";

    protected List<String> loadCommands(ConfigurationMap map) throws InventoryConfigException {

        if (!map.isList(COMMANDS_KEY)) {
            throw new InventoryConfigException("Property '%s' not found or is not a list for action '%s' at '%s'".formatted(COMMANDS_KEY, this.getName(), map.getCurrentPath()));
        }

        return map.getStringList(COMMANDS_KEY).stream()
                .map(command -> command.startsWith("/") ? command.substring(1) : command)
                .toList();
    }
}
