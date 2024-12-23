package com.github.syr0ws.craftventory.common.transform.provider.pagination;

import com.github.syr0ws.craftventory.api.config.PaginationConfig;
import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.transform.dto.pagination.PaginationDto;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("RawUseOfParameterized")
public class PaginationProvider<T> extends AbstractPaginationDataProvider<PaginationDto> {

    private final String paginationId;
    private final Class<T> paginationDataType;
    private final Function<CraftVentory, List<T>> dataSupplier;

    public PaginationProvider(String paginationId, Class<T> paginationDataType, Function<CraftVentory, List<T>> dataSupplier) {

        if (paginationId == null || paginationId.isEmpty()) {
            throw new IllegalArgumentException("paginationId cannot be null or empty");
        }

        if (paginationDataType == null) {
            throw new IllegalArgumentException("paginationDataType cannot be null");
        }

        if (dataSupplier == null) {
            throw new IllegalArgumentException("dataSupplier cannot be null");
        }

        this.paginationId = paginationId;
        this.paginationDataType = paginationDataType;
        this.dataSupplier = dataSupplier;
    }

    @Override
    public PaginationDto<?> provide(InventoryProvider provider, Context context) {

        // Data retrieval from configuration
        PaginationConfig paginationConfig = super.getPaginationConfigById(this.paginationId, provider, context);

        // DTO creation
        PaginationDto<?> dto = new PaginationDto<>(
                paginationConfig.getId(),
                paginationConfig.getSlots(),
                this.paginationDataType,
                this.dataSupplier
        );

        // Enhancement
        EnhancementManager enhancementManager = provider.getEnhancementManager();
        enhancementManager.enhance(dto, PaginationDto.class, context);

        return dto;
    }

    @Override
    public String getName() {
        return this.paginationId;
    }

    @Override
    public Class<PaginationDto> getDTOClass() {
        return PaginationDto.class;
    }
}
