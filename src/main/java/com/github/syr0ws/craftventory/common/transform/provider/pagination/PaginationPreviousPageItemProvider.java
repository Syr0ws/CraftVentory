package com.github.syr0ws.craftventory.common.transform.provider.pagination;

import com.github.syr0ws.craftventory.api.config.model.InventoryItemConfig;
import com.github.syr0ws.craftventory.api.config.model.pagination.PaginationConfig;
import com.github.syr0ws.craftventory.api.config.model.InventoryPatternConfig;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.item.ItemParser;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.transform.dto.InventoryItemDto;
import com.github.syr0ws.craftventory.common.transform.dto.pagination.PaginationPageItemDto;
import com.github.syr0ws.craftventory.common.transform.provider.ProviderNameEnum;

import java.util.HashSet;
import java.util.List;

public class PaginationPreviousPageItemProvider extends AbstractPaginationPageItemProvider {

    public PaginationPreviousPageItemProvider(ItemParser itemParser) {
        super(itemParser);
    }

    @Override
    public InventoryItemDto provide(InventoryProvider provider, Context context) {

        // Data retrieval from configuration
        PaginationConfig paginationConfig = super.getPaginationConfig(provider, context);
        InventoryItemConfig itemConfig = paginationConfig.getPreviousPageItem().getNavItem();

        InventoryPatternConfig pattern = provider.getConfig().getPattern();
        List<Integer> slots = pattern.getSlots(paginationConfig.getPreviousPageItem().getSymbol());

        // DTO creation
        InventoryItemDto dto = new PaginationPageItemDto(
                itemConfig.getItemId(),
                itemConfig.getItem().build(),
                itemConfig.getActions(),
                new HashSet<>(slots),
                paginationConfig.getPaginationId(),
                PaginationPageItemDto.PageItemType.PREVIOUS_PAGE
        );

        super.process(provider, context, dto);

        return dto;
    }

    @Override
    public String getName() {
        return ProviderNameEnum.PAGINATION_PREVIOUS_PAGE_ITEM.name();
    }

    @Override
    public Class<InventoryItemDto> getDTOClass() {
        return InventoryItemDto.class;
    }
}
