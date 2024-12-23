package com.github.syr0ws.craftventory.api.inventory.event;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.util.Context;
import org.bukkit.event.Cancellable;

/**
 * Represents an event that is triggered before a {@link CraftVentory} is opened.
 */
public class CraftVentoryBeforeOpenEvent extends CraftVentoryOpenEvent implements Cancellable {

    private final Context context;
    private boolean cancelled;

    /**
     * Constructs a new {@link CraftVentoryBeforeOpenEvent}.
     *
     * @param inventory The {@link CraftVentory} instance associated with this event.
     * @param viewer    The player who triggered this event.
     * @param context   A context that contains additional data.
     * @throws IllegalArgumentException If any parameter is null.
     */

    /**
     * @see CraftVentoryEvent#CraftVentoryEvent(CraftVentory, InventoryViewer)
     */
    public CraftVentoryBeforeOpenEvent(CraftVentory inventory, InventoryViewer viewer, Context context) {
        super(inventory, viewer);

        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }

        this.context = context;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Context getContext() {
        return this.context;
    }
}
