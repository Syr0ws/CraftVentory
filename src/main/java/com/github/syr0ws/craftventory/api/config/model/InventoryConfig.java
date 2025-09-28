package com.github.syr0ws.craftventory.api.config.model;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationsConfig;
import com.github.syr0ws.craftventory.api.inventory.CraftVentoryType;

/**
 * Represents the configuration of an inventory.
 */
public class InventoryConfig {

    private final String inventoryId;
    private final String title;
    private final CraftVentoryType type;
    private final InventoryPatternConfig pattern;
    private final InventoryContentConfig content;
    private final PaginationsConfig paginations;

    /**
     * Constructs an InventoryConfig with the specified parameters.
     *
     * @param inventoryId the unique identifier of the inventory
     * @param title       the title of the inventory
     * @param type        the type of the inventory
     * @param pattern     the pattern configuration of the inventory
     * @param content     the content configuration of the inventory
     * @param paginations the pagination configurations holder of the inventory
     * @throws IllegalArgumentException if any parameter is null or empty (for strings)
     */
    public InventoryConfig(String inventoryId, String title, CraftVentoryType type, InventoryPatternConfig pattern, InventoryContentConfig content, PaginationsConfig paginations) {
        Validate.notEmpty(inventoryId, "inventoryId cannot be null or empty");
        Validate.notNull(title, "title cannot be null");
        Validate.notNull(type, "type cannot be null");
        Validate.notNull(pattern, "pattern cannot be null");
        Validate.notNull(content, "content cannot be null");
        Validate.notNull(paginations, "paginations cannot be null");

        this.inventoryId = inventoryId;
        this.title = title;
        this.type = type;
        this.pattern = pattern;
        this.content = content;
        this.paginations = paginations;
    }

    /**
     * Gets the unique identifier of the inventory.
     *
     * @return the inventory ID
     */
    public String getInventoryId() {
        return this.inventoryId;
    }

    /**
     * Gets the title of the inventory.
     *
     * @return the inventory title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the type of the inventory.
     *
     * @return the inventory type
     */
    public CraftVentoryType getType() {
        return this.type;
    }

    /**
     * Gets the pattern configuration of the inventory.
     *
     * @return the inventory pattern configuration
     */
    public InventoryPatternConfig getPattern() {
        return this.pattern;
    }

    /**
     * Gets the content configuration of the inventory.
     *
     * @return the inventory content configuration
     */
    public InventoryContentConfig getContent() {
        return this.content;
    }

    /**
     * Gets the pagination configurations holder of the inventory.
     *
     * @return the inventory pagination settings
     */
    public PaginationsConfig getPaginations() {
        return this.paginations;
    }
}
