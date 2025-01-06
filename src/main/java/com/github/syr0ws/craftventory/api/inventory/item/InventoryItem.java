package com.github.syr0ws.craftventory.api.inventory.item;

import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.data.DataStore;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Represents an item within an inventory.
 */
public interface InventoryItem {

    /**
     * Retrieves the unique id of this inventory item.
     *
     * @return The id of this item. Must not be {@code null}.
     */
    String getId();

    /**
     * Retrieves the corresponding {@link ItemStack} set in the inventory.
     *
     * @return Retrieves the corresponding {@link ItemStack} set in the inventory.
     */
    ItemStack getItemStack();

    /**
     * Retrieves the list of {@link ClickAction} associated with this inventory item.
     * <p>
     * These actions define the behavior of the item when clicked. Multiple actions may be
     * triggered depending on the specific click type.
     * </p>
     *
     * @return A list of {@link ClickAction}. Never {@code null}.
     */
    List<ClickAction> getActions();

    /**
     * Retrieves the local data store for the item.
     *
     * @return The {@link DataStore} instance associated with the item. Never {@code null}.
     */
    DataStore getLocalStore();

    /**
     * Enable the item. When an item is enabled, its actions are executed when a player clicks on it.
     */
    void enable();

    /**
     * Disable the item. When an item is disabled, its actions are not executed when a player clicks on it.
     */
    void disable();

    /**
     * Check if an item is enabled.
     * @return {@code true} if the item is enabled, {@code false} otherwise.
     */
    boolean isEnabled();
}
