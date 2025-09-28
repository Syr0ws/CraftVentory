package com.github.syr0ws.craftventory.api.config.model.pagination;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemConfig;
import com.github.syr0ws.craftventory.api.inventory.pagination.PaginationType;

import java.util.List;

/**
 * Represents a static pagination configuration.
 *
 * <p>In static pagination, the items displayed in the paginated section are predefined and do not change dynamically.</p>
 */
public class StaticPaginationConfig extends PaginationConfig {

    private final List<InventoryItemConfig> paginationItems;

    /**
     * Constructs a {@link StaticPaginationConfig} with the specified parameters.
     *
     * @param paginationId      the unique identifier for the pagination
     * @param previousPageItem  the configuration for the "previous page" navigation item
     * @param nextPageItem      the configuration for the "next page" navigation item
     * @param symbol            the character symbol representing the pagination in the inventory pattern
     * @param paginationItems   the list of items to be displayed in the paginated section
     * @throws IllegalArgumentException if any of the parameters are null or invalid
     */
    public StaticPaginationConfig(String paginationId, PaginationNavItemConfig previousPageItem, PaginationNavItemConfig nextPageItem, char symbol, List<InventoryItemConfig> paginationItems) {
        super(paginationId, previousPageItem, nextPageItem, symbol);
        Validate.notNull(paginationItems, "items cannot be null");
        this.paginationItems = List.copyOf(paginationItems);
    }

    @Override
    public PaginationType getPaginationType() {
        return PaginationType.STATIC;
    }

    /**
     * Retrieves the list of items to be displayed in the paginated section.
     *
     * @return an unmodifiable list of pagination items
     */
    public List<InventoryItemConfig> getPaginationItems() {
        return this.paginationItems;
    }
}
