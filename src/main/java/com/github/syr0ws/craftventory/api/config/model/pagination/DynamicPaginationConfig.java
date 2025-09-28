package com.github.syr0ws.craftventory.api.config.model.pagination;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.model.InventoryItemConfig;
import com.github.syr0ws.craftventory.api.inventory.pagination.PaginationType;

/**
 * Represents a dynamic pagination configuration.
 *
 * <p>In dynamic pagination, the items displayed in the paginated section can change based on external data.</p>
 */
public class DynamicPaginationConfig extends PaginationConfig {

    private final InventoryItemConfig paginationItem;

    /**
     * Constructs a {@link DynamicPaginationConfig} with the specified parameters.
     *
     * @param paginationId      the unique identifier for the pagination
     * @param previousPageItem  the configuration for the "previous page" navigation item
     * @param nextPageItem      the configuration for the "next page" navigation item
     * @param symbol            the character symbol representing the pagination in the inventory pattern
     * @param paginationItem    the item to be used as a template for each entry in the paginated section
     * @throws IllegalArgumentException if any of the parameters are null or invalid
     */
    public DynamicPaginationConfig(String paginationId, PaginationNavItemConfig previousPageItem, PaginationNavItemConfig nextPageItem, char symbol, InventoryItemConfig paginationItem) {
        super(paginationId, previousPageItem, nextPageItem, symbol);
        Validate.notNull(paginationItem, "paginationItem cannot be null");
        this.paginationItem = paginationItem;
    }

    @Override
    public PaginationType getPaginationType() {
        return PaginationType.DYNAMIC;
    }

    /**
     * Retrieves the item to be used as a template for each entry in the paginated section.
     *
     * @return the pagination item configuration
     */
    public InventoryItemConfig getPaginationItem() {
        return this.paginationItem;
    }
}
