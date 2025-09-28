package com.github.syr0ws.craftventory.internal.config.loader.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;
import com.github.syr0ws.craftventory.common.inventory.action.MessageAction;

import java.util.List;
import java.util.Set;

public class MessageActionLoader extends YamlCommonActionLoader {

    private static final String MESSAGES_KEY = "messages";

    @Override
    public ClickAction load(ConfigurationMap map) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(map);

        if (!map.isList(MESSAGES_KEY)) {
            throw new InventoryConfigException("Property '%s' not found or is not a list for action '%s' at '%s'".formatted(MESSAGES_KEY, this.getName(), map.getCurrentPath()));
        }

        List<String> messages = map.getStringList(MESSAGES_KEY);

        return new MessageAction(clickTypes, messages);
    }

    @Override
    public String getName() {
        return MessageAction.ACTION_NAME;
    }
}
