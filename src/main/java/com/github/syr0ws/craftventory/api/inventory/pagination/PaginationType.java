package com.github.syr0ws.craftventory.api.inventory.pagination;

/**
 * Represents the type of pagination used in an inventory.
 */
public enum PaginationType {

    /** Static pagination, where the items displayed are fixed. */
    STATIC,

    /** Dynamic pagination, where the items displayed can change based on external data. */
    DYNAMIC
}
