package com.github.syr0ws.craftventory.api.inventory.event;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;

/**
 * Represents a base class for all events related to a {@link CraftVentory}.
 */
public abstract class CraftVentoryEvent {

    private final CraftVentory inventory;
    private final InventoryViewer viewer;

    /**
     * Constructs a new {@link CraftVentoryEvent}.
     *
     * @param inventory The {@link CraftVentory} instance associated with this event.
     * @param viewer    The player who triggered this event.
     * @throws IllegalArgumentException If any parameter is null.
     */
    public CraftVentoryEvent(CraftVentory inventory, InventoryViewer viewer) {

        if (inventory == null) {
            throw new IllegalArgumentException("inventory cannot be null");
        }

        if (viewer == null) {
            throw new IllegalArgumentException("viewer cannot be null");
        }

        this.inventory = inventory;
        this.viewer = viewer;
    }

    /**
     * Retrieves the {@link CraftVentory} associated with this event.
     *
     * @return The {@link CraftVentory} instance.
     */
    public CraftVentory getInventory() {
        return this.inventory;
    }

    /**
     * Retrieves the {@link InventoryViewer} that triggered this event.
     *
     * @return The {@link InventoryViewer} instance.
     */
    public InventoryViewer getViewer() {
        return this.viewer;
    }
}
