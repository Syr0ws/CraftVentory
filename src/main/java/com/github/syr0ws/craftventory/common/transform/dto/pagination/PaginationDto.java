package com.github.syr0ws.craftventory.common.transform.dto.pagination;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.transform.dto.DTO;
import com.github.syr0ws.craftventory.common.transform.dto.DtoNameEnum;
import com.github.syr0ws.craftventory.internal.inventory.pagination.SimplePagination;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public record PaginationDto<T>(String paginationId,
                               List<Integer> slots,
                               Class<T> paginationDataType,
                               BiFunction<CraftVentory, SimplePagination<T>, List<T>> dataSupplier) implements DTO {

    @Override
    public String getId() {
        return DtoNameEnum.PAGINATION.name();
    }
}
