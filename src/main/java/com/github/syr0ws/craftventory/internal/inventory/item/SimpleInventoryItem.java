package com.github.syr0ws.craftventory.internal.inventory.item;

import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.data.DataStore;
import com.github.syr0ws.craftventory.api.inventory.item.InventoryItem;
import com.github.syr0ws.craftventory.internal.inventory.data.SimpleDataStore;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class SimpleInventoryItem implements InventoryItem {

    private final String id;
    private final ItemStack item;
    private final List<ClickAction> actions;
    private final DataStore dataStore;
    private boolean enabled = true;

    public SimpleInventoryItem(String id, ItemStack item, List<ClickAction> actions) {

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }

        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        if (actions == null) {
            throw new IllegalArgumentException("actions cannot be null");
        }

        this.id = id;
        this.item = item;
        this.actions = Collections.unmodifiableList(actions);
        this.dataStore = new SimpleDataStore();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public ItemStack getItemStack() {
        return this.item;
    }

    @Override
    public List<ClickAction> getActions() {
        return this.actions;
    }

    @Override
    public DataStore getLocalStore() {
        return this.dataStore;
    }

    @Override
    public void enable() {
        this.enabled = true;
    }

    @Override
    public void disable() {
        this.enabled = false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
