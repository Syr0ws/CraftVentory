package com.github.syr0ws.craftventory.api.config.model;

import com.github.syr0ws.crafter.util.Validate;

import java.util.List;
import java.util.Optional;

/**
 * Represents the configuration of the content of an inventory.
 */
public class InventoryContentConfig {

    private final List<InventoryContentItemConfig> items;

    /**
     * Constructs an {@link InventoryContentConfig} with the specified list of item configurations.
     *
     * @param items the list of item configurations
     * @throws IllegalArgumentException if the items list is null
     */
    public InventoryContentConfig(List<InventoryContentItemConfig> items) {
        Validate.notNull(items, "items cannot be null");
        this.items = List.copyOf(items);
    }

    /**
     * Retrieves an item configuration by its symbol.
     *
     * @param symbol the symbol representing the item in the inventory pattern
     * @return an {@link Optional} containing the matching item configuration, or an empty {@link Optional} if no match is found
     */
    public Optional<InventoryContentItemConfig> getItemBySymbol(char symbol) {
        return this.items.stream()
                .filter(item -> item.getSymbol() == symbol)
                .findFirst();
    }

    /**
     * Retrieves the list of all item configurations.
     *
     * @return an unmodifiable list of item configurations
     */
    public List<InventoryContentItemConfig> getItems() {
        return this.items;
    }
}
