package com.github.syr0ws.craftventory.api.config.model.pagination;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemConfig;

import java.util.Optional;

/**
 * Represents the configuration for a pagination navigation item, such as "previous page" or "next page".
 */
public class PaginationNavItemConfig {

    private final InventoryItemConfig navItem;
    private final InventoryItemConfig noNavItem;
    private final char symbol;

    /**
     * Constructs a {@link PaginationNavItemConfig} with the specified parameters.
     *
     * @param navItem   the item to display when navigation is possible
     * @param noNavItem the item to display when navigation is not possible
     * @param symbol    the character symbol representing the navigation item in the inventory pattern
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public PaginationNavItemConfig(InventoryItemConfig navItem, InventoryItemConfig noNavItem, char symbol) {
        Validate.notNull(navItem, "navItem cannot be null");

        this.navItem = navItem;
        this.noNavItem = noNavItem;
        this.symbol = symbol;
    }

    /**
     * Retrieves the item to display when navigation is possible.
     *
     * @return the navigation item configuration
     */
    public InventoryItemConfig getNavItem() {
        return this.navItem;
    }

    /**
     * Retrieves the item to display when navigation is not possible, if defined.
     *
     * @return an {@link Optional} containing the no-navigation item configuration, or empty if not defined
     */
    public Optional<InventoryItemConfig> getNoNavItem() {
        return Optional.ofNullable(this.noNavItem);
    }

    /**
     * Retrieves the character symbol representing the navigation item in the inventory pattern.
     *
     * @return the symbol character
     */
    public char getSymbol() {
        return this.symbol;
    }
}
