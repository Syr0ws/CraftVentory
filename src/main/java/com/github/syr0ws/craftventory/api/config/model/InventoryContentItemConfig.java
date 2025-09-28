package com.github.syr0ws.craftventory.api.config.model;

import com.github.syr0ws.craftstack.item.Item;

/**
 * Represents an item configuration within an inventory.
 */
public class InventoryContentItemConfig extends InventoryItemConfig {

    private final char symbol;

    /**
     * Constructs an InventoryContentItemConfig with the specified item, actions configuration, and symbol.
     *
     * @param itemId the unique identifier for this inventory item
     * @param item   the item associated with this configuration
     * @param config the actions configuration for this inventory item
     * @param symbol the symbol representing this item in the inventory pattern
     * @throws IllegalArgumentException if item or config is null
     */
    public InventoryContentItemConfig(String itemId, Item item, InventoryItemActionsConfig config, char symbol) {
        super(itemId, item, config);
        this.symbol = symbol;
    }

    /**
     * Gets symbol associated with this item in the inventory pattern.
     *
     * @return the symbol character
     */
    public char getSymbol() {
        return this.symbol;
    }
}
