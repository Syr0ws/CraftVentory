package com.github.syr0ws.craftventory.api.config.model.pagination;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.inventory.pagination.PaginationType;

/**
 * Abstract base class for pagination configurations.
 */
public abstract class PaginationConfig {

    private final String paginationId;
    private final PaginationNavItemConfig previousPageItem;
    private final PaginationNavItemConfig nextPageItem;
    private final char symbol;

    /**
     * Constructs a {@link PaginationConfig} with the specified parameters.
     *
     * @param paginationId      the unique identifier for the pagination
     * @param previousPageItem  the configuration for the "previous page" navigation item
     * @param nextPageItem      the configuration for the "next page" navigation item
     * @param symbol            the character symbol representing the pagination in the inventory pattern
     * @throws IllegalArgumentException if any of the parameters are null or invalid
     */
    public PaginationConfig(String paginationId, PaginationNavItemConfig previousPageItem, PaginationNavItemConfig nextPageItem, char symbol) {
        Validate.notEmpty(paginationId, "paginationId cannot be null or empty");
        Validate.notNull(previousPageItem, "previousPageItem cannot be null");
        Validate.notNull(nextPageItem, "nextPageItem cannot be null");

        this.paginationId = paginationId;
        this.previousPageItem = previousPageItem;
        this.nextPageItem = nextPageItem;
        this.symbol = symbol;
    }

    /**
     * Retrieves the type of pagination.
     *
     * @return the pagination type
     */
    public abstract PaginationType getPaginationType();

    /**
     * Retrieves the unique identifier for the pagination.
     *
     * @return the pagination ID
     */
    public String getPaginationId() {
        return this.paginationId;
    }

    /**
     * Retrieves the configuration for the "previous page" navigation item.
     *
     * @return the previous page item configuration
     */
    public PaginationNavItemConfig getPreviousPageItem() {
        return this.previousPageItem;
    }

    /**
     * Retrieves the configuration for the "next page" navigation item.
     *
     * @return the next page item configuration
     */
    public PaginationNavItemConfig getNextPageItem() {
        return this.nextPageItem;
    }

    /**
     * Retrieves the character symbol representing the where paginated items are set in the inventory pattern.
     *
     * @return the pagination symbol
     */
    public char getSymbol() {
        return this.symbol;
    }
}
