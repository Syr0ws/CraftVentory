package com.github.syr0ws.craftventory.api.config.model;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftstack.item.Item;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;

import java.util.List;

/**
 * Represents the configuration for an inventory item.
 */
public class InventoryItemConfig {

    private final String itemId;
    private final Item item;
    private final InventoryItemActionsConfig actions;

    /**
     * Creates a new InventoryItemConfig with the specified item and actions.
     * @param item the item associated with this configuration
     * @param actions the actions configuration for this inventory item
     * @throws IllegalArgumentException if item or actions is null
     */
    public InventoryItemConfig(String itemId, Item item, InventoryItemActionsConfig actions) {
        Validate.notEmpty(itemId, "itemId cannot be null or empty");
        Validate.notNull(item, "item cannot be null");
        Validate.notNull(actions, "actions cannot be null");

        this.itemId = itemId;
        this.item = item;
        this.actions = actions;
    }

    /**
     * Retrieves the unique identifier for the inventory item.
     *
     * @return the unique identifier for the inventory item
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Returns a copy of the item associated with this configuration.
     *
     * @return a copy of the item
     */
    public Item getItem() {
        return this.item.copy();
    }

    /**
     * Returns the actions configuration associated with this inventory item.
     *
     * @return the actions configuration
     */
    public List<ClickAction> getActions() {
        return this.actions.getActions();
    }
}
