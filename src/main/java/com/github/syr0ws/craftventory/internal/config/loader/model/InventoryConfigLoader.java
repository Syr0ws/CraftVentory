package com.github.syr0ws.craftventory.internal.config.loader.model;

import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.config.loader.ConfigLoader;
import com.github.syr0ws.craftventory.api.config.model.InventoryConfig;
import com.github.syr0ws.craftventory.api.config.model.InventoryContentConfig;
import com.github.syr0ws.craftventory.api.config.model.InventoryContentItemConfig;
import com.github.syr0ws.craftventory.api.config.model.InventoryPatternConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationsConfig;
import com.github.syr0ws.craftventory.api.inventory.CraftVentoryType;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderManager;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderRegistry;
import com.github.syr0ws.craftventory.internal.config.loader.ConfigLoaderUtils;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class InventoryConfigLoader implements ConfigLoader<InventoryConfig> {

    private static final String PROPERTY_INVENTORY_ID = "inventory-id";
    private static final String PROPERTY_TITLE = "title";
    private static final String PROPERTY_TYPE = "type";
    private static final String PROPERTY_PATTERN = "pattern";
    private static final String PROPERTY_CONTENT = "content";
    private static final String PROPERTY_PAGINATIONS = "paginations";

    private final ConfigLoaderManager manager;

    public InventoryConfigLoader(ConfigLoaderManager manager) {
        this.manager = manager;
    }

    @Override
    public InventoryConfig load(ConfigurationSection section) throws InventoryConfigException {
        return null;
    }

    @Override
    public Class<InventoryConfig> getType() {
        return InventoryConfig.class;
    }

    @Override
    public String getName() {
        return ConfigLoaderRegistry.INVENTORY;
    }

    private String loadInventoryId(ConfigurationSection section) throws InventoryConfigException {

        if(!section.isString(PROPERTY_INVENTORY_ID)) {
            throw new InventoryConfigException("Property '%s.%s' not found or is not a string".formatted(section.getCurrentPath(), PROPERTY_INVENTORY_ID));
        }

        return section.getString(PROPERTY_INVENTORY_ID, "");
    }

    private String loadTitle(ConfigurationSection section) throws InventoryConfigException {

        if(!section.isString(PROPERTY_TITLE)) {
            throw new InventoryConfigException("Property '%s.%s' not found or is not a string".formatted(section.getCurrentPath(), PROPERTY_TITLE));
        }

        return section.getString(PROPERTY_TITLE, "");
    }

    private CraftVentoryType loadInventoryType(ConfigurationSection section) throws InventoryConfigException {

        String typeAsString = section.getString(PROPERTY_TYPE);

        if (typeAsString == null) {
            throw new InventoryConfigException(String.format("Property '%s.%s' not found or is not a string", section.getCurrentPath(), PROPERTY_TYPE));
        }

        if(!CraftVentoryType.isValidType(typeAsString)) {
            throw new InventoryConfigException(String.format("Invalid inventory type '%s' at '%s.%s'", typeAsString, section.getCurrentPath(), PROPERTY_TYPE));
        }

        return CraftVentoryType.valueOf(typeAsString);
    }

    private InventoryPatternConfig loadPattern(ConfigurationSection section, CraftVentoryType type) throws InventoryConfigException {

        if(!section.isList(PROPERTY_PATTERN)) {
            throw new InventoryConfigException(String.format("Property '%s.%s' not found or is not a list", section.getCurrentPath(), PROPERTY_PATTERN));
        }

        List<String> pattern = section.getStringList(PROPERTY_PATTERN);

        // Pattern validation.
        if (pattern.size() != type.getRows()) {
            throw new InventoryConfigException(String.format("Invalid property '%s.%s' : number of rows must be %d", section.getCurrentPath(), PROPERTY_PATTERN, type.getRows()));
        }

        boolean hasInvalidRow = pattern.stream()
                .anyMatch(line -> line.length() != type.getColumns());

        if (hasInvalidRow) {
            throw new InventoryConfigException(String.format("Invalid property '%s.%s' : some lines do not have %d characters", section.getCurrentPath(), PROPERTY_PATTERN, type.getColumns()));
        }

        return new InventoryPatternConfig(pattern, type);
    }

    private InventoryContentConfig loadContent(ConfigurationSection section) throws InventoryConfigException {

        ConfigLoader<InventoryContentItemConfig> loader = this.manager.getLoader(
                ConfigLoaderRegistry.INVENTORY_CONTENT_ITEM, InventoryContentItemConfig.class);

        List<InventoryContentItemConfig> items = ConfigLoaderUtils.loadObjects(section, PROPERTY_CONTENT, loader);

        return new InventoryContentConfig(items);
    }

    private PaginationsConfig loadPaginations(ConfigurationSection section) throws InventoryConfigException {

        ConfigLoader<PaginationConfig> loader = this.manager.getLoader(ConfigLoaderRegistry.PAGINATION, PaginationConfig.class);
        List<PaginationConfig> paginations = ConfigLoaderUtils.loadObjects(section, PROPERTY_PAGINATIONS, loader);

        return new PaginationsConfig(paginations);
    }
}
