package com.github.syr0ws.craftventory.common.transform.provider.pagination;

import com.github.syr0ws.craftventory.api.config.InventoryConfig;
import com.github.syr0ws.craftventory.api.config.PaginationConfig;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.dto.DTO;
import com.github.syr0ws.craftventory.api.transform.provider.Provider;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;

public abstract class AbstractPaginationDataProvider<T extends DTO> implements Provider<T> {

    protected PaginationConfig getPaginationConfig(InventoryProvider provider, Context context) {

        InventoryConfig config = provider.getConfig();

        String paginationId = context.findData(CommonContextKey.PAGINATION_ID.name(), String.class)
                .orElseThrow(() -> new NullPointerException("No pagination id found in the context"));

        return config.getPaginationConfig(paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination found with id '%s' in the configuration", paginationId)));
    }

    protected PaginationConfig getPaginationConfigById(String paginationId, InventoryProvider provider, Context context) {

        InventoryConfig config = provider.getConfig();

        return config.getPaginationConfig(paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination found with id '%s' in the configuration", paginationId)));
    }
}
