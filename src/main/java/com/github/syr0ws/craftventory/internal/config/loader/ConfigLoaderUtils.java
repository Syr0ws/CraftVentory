package com.github.syr0ws.craftventory.internal.config.loader;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ConfigLoaderUtils {

    public static <T> List<T> loadObjects(ConfigurationSection parent, String sectionName, ConfigLoader<T> loader) throws InventoryConfigException {
        Validate.notNull(parent, "parent cannot be null");
        Validate.notEmpty(sectionName, "sectionName cannot be null or empty");
        Validate.notNull(loader, "loader cannot be null");

        ConfigurationSection section = parent.getConfigurationSection(sectionName);

        if(section == null) {
            throw new InventoryConfigException("Section '%s.%s' not found".formatted(parent.getCurrentPath(), sectionName));
        }

        return loadObjects(section, loader);
    }

    public static <T> List<T> loadObjects(ConfigurationSection section, ConfigLoader<T> loader) throws InventoryConfigException {
        Validate.notNull(section, "section cannot be null");
        Validate.notNull(loader, "loader cannot be null");

        List<T> objects = new ArrayList<>();

        for(String key : section.getKeys(false)) {

            ConfigurationSection objectSection = section.getConfigurationSection(key);

            if(objectSection == null) {
                throw new InventoryConfigException("Key '%s.%s' must be a section".formatted(section.getCurrentPath(), key));
            }

            T object = loader.load(objectSection);
            objects.add(object);
        }

        return objects;
    }
}
