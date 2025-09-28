package com.github.syr0ws.craftventory.api.config.loader;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import org.bukkit.configuration.ConfigurationSection;

public interface ConfigLoader<T> {

    T load(ConfigurationSection section) throws InventoryConfigException;

    Class<T> getType();

    String getName();
}
