package com.github.syr0ws.craftventory.internal.config.loader.model;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import org.bukkit.configuration.ConfigurationSection;

public class SymbolLoader implements ConfigLoader<Character> {

    private static final String PROPERTY_SYMBOL = "symbol";

    @Override
    public Character load(ConfigurationSection section) throws InventoryConfigException {
        Validate.notNull(section, "section cannot be null");

        if(!section.isString(PROPERTY_SYMBOL)) {
            throw new InventoryConfigException("Property '%s.%s' not found or is not a string".formatted(section.getCurrentPath(), PROPERTY_SYMBOL));
        }

        String symbol = section.getString(PROPERTY_SYMBOL, "");

        if(symbol.length() != 1) {
            throw new InventoryConfigException("Property '%s.%s' must be a single character".formatted(section.getCurrentPath(), PROPERTY_SYMBOL));
        }

        return symbol.charAt(0);
    }

    @Override
    public Class<Character> getType() {
        return Character.class;
    }

    @Override
    public String getName() {
        return ConfigLoaderRegistry.SYMBOL;
    }
}
