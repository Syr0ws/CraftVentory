package com.github.syr0ws.fastinventory.internal.inventory.pagination;

import com.github.syr0ws.fastinventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.fastinventory.api.inventory.pagination.PaginationManager;

import java.util.*;

public class SimplePaginationManager implements PaginationManager {

    private final Map<String, Pagination<?>> paginations = new HashMap<>();

    @Override
    public void addPagination(Pagination<?> pagination) {

        if(pagination == null) {
            throw new IllegalArgumentException("pagination cannot be null");
        }

        this.paginations.put(pagination.getId(), pagination);
    }

    @Override
    public boolean removePagination(String paginationId) {

        if(paginationId == null || paginationId.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }

        return this.paginations.remove(paginationId) != null;
    }

    @Override
    public void updatePaginations() {
        this.paginations.values().forEach(Pagination::update);
    }

    @Override
    public Optional<Pagination<?>> getPagination(String paginationId) {

        if(paginationId == null || paginationId.isEmpty()) {
            throw new IllegalArgumentException("paginationId cannot be null or empty");
        }

        return Optional.ofNullable(this.paginations.get(paginationId));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<Pagination<T>> getPagination(String paginationId, Class<T> type) {

        if(paginationId == null || paginationId.isEmpty()) {
            throw new IllegalArgumentException("paginationId cannot be null or empty");
        }

        if(type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        Pagination<?> pagination = this.paginations.get(paginationId);

        if (pagination == null) {
            return Optional.empty();
        }

        Class<?> dataType = pagination.getModel().getDataType();

        return dataType.equals(type) ? Optional.of((Pagination<T>) pagination) : Optional.empty();
    }

    @Override
    public List<Pagination<?>> getPaginations() {
        return List.copyOf(this.paginations.values());
    }
}