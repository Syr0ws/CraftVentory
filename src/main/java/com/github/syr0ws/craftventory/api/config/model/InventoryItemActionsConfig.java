package com.github.syr0ws.craftventory.api.config.model;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;

import java.util.List;

public class InventoryItemActionsConfig {

    private final List<ClickAction> actions;

    public InventoryItemActionsConfig(List<ClickAction> actions) {
        Validate.notNull(actions, "actions cannot be null");
        this.actions = List.copyOf(actions);
    }

    public List<ClickAction> getActions() {
        return this.actions;
    }
}
