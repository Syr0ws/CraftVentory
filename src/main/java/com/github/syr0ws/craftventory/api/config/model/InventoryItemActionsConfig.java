package com.github.syr0ws.craftventory.api.config.model;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;

import java.util.List;

/**
 * Represents the configuration for inventory item actions.
 */
public class InventoryItemActionsConfig {

    private final List<ClickAction> actions;

    /**
     * Constructs an {@code InventoryItemActionsConfig} with the specified actions.
     *
     * @param actions the list of {@link ClickAction} instances
     * @throws NullPointerException if {@code actions} is null
     */
    public InventoryItemActionsConfig(List<ClickAction> actions) {
        Validate.notNull(actions, "actions cannot be null");
        this.actions = List.copyOf(actions);
    }

    /**
     * Retrieves the list of {@link ClickAction} instances.
     *
     * @return an unmodifiable list of click actions
     */
    public List<ClickAction> getActions() {
        return this.actions;
    }
}
