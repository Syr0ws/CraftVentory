package com.github.syr0ws.craftventory.common.config.yaml;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.loader.action.ClickActionLoader;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class YamlCommonActionLoader implements ClickActionLoader {

    private static final String CLICK_TYPES_KEY = "click-types";

    protected Set<ClickType> loadClickTypes(ConfigurationMap map) throws InventoryConfigException {

        // If the property is not set, all clicks are handled by default.
        if (!map.isSet(CLICK_TYPES_KEY)) {
            return Collections.emptySet();
        }

        // When the property is set, checking that it is a list.
        if (!map.isList(CLICK_TYPES_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' is not a list for action '%s' at '%s'", CLICK_TYPES_KEY, this.getName(), map.getCurrentPath()));
        }

        List<String> clickTypeNames = map.getStringList(CLICK_TYPES_KEY);
        Set<ClickType> clickTypes = new HashSet<>();

        for (String clickTypeName : clickTypeNames) {

            String uppercaseClickTypeName = clickTypeName.toUpperCase();

            if (!ClickType.isValidClickType(uppercaseClickTypeName)) {
                throw new InventoryConfigException(String.format("Invalid click type '%s' for action '%s' at '%s'", clickTypeName, this.getName(), map.getCurrentPath()));
            }

            ClickType clickType = ClickType.valueOf(uppercaseClickTypeName);
            clickTypes.add(clickType);
        }

        return clickTypes;
    }
}
