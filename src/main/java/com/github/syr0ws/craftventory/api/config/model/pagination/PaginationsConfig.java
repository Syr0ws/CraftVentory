package com.github.syr0ws.craftventory.api.config.model.pagination;

import com.github.syr0ws.crafter.util.Validate;

import java.util.List;
import java.util.Optional;

/**
 * Represents a collection of pagination configurations.
 */
public class PaginationsConfig {

    private final List<PaginationConfig> paginations;

    /**
     * Constructs a {@link PaginationsConfig} with the specified list of pagination configurations.
     *
     * @param paginations the list of pagination configurations
     * @throws IllegalArgumentException if the paginations list is null
     */
    public PaginationsConfig(List<PaginationConfig> paginations) {
        Validate.notNull(paginations, "paginations cannot be null");
        this.paginations = List.copyOf(paginations);
    }

    /**
     * Retrieves the list of all pagination configurations.
     *
     * @return an unmodifiable list of pagination configurations
     */
    public List<PaginationConfig> getPaginations() {
        return this.paginations;
    }

    /**
     * Retrieves a pagination configuration by its ID.
     *
     * @param paginationId the ID of the pagination configuration
     * @return an {@link Optional} containing the matching pagination configuration, or an empty {@link Optional} if no match is found
     * @throws IllegalArgumentException if paginationId is null or empty
     */
    public Optional<PaginationConfig> getPagination(String paginationId) {
        Validate.notEmpty(paginationId, "paginationId cannot be null or empty");
        return this.paginations.stream()
                .filter(pagination -> pagination.getPaginationId().equals(paginationId))
                .findFirst();
    }
}
