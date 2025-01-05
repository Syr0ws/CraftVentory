package com.github.syr0ws.craftventory.common.transform.provider;

import com.github.syr0ws.craftventory.api.config.InventoryConfig;
import com.github.syr0ws.craftventory.api.config.InventoryItemConfig;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.item.ItemParser;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.transform.dto.InventoryItemDto;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;

import java.util.Collections;

public class InventoryItemProviderBySlot extends AbstractItemProvider<InventoryItemDto> {

    public InventoryItemProviderBySlot(ItemParser itemParser) {
        super(itemParser);
    }

    @Override
    public InventoryItemDto provide(InventoryProvider provider, Context context) {

        // Data retrieval from configuration
        InventoryConfig config = provider.getConfig();

        Integer slot = context.findData(CommonContextKey.SLOT.name(), Integer.class)
                .orElseThrow(() -> new NullPointerException("No slot found in the context"));

        InventoryItemConfig itemConfig = config.getContent().get(slot);

        // DTO creation
        InventoryItemDto dto = itemConfig == null ?
                new InventoryItemDto() :
                new InventoryItemDto(itemConfig.getId(), itemConfig.getItemStack(), itemConfig.getActions(), Collections.singleton(slot));

        super.process(provider, context, dto);

        return dto;
    }

    @Override
    public String getName() {
        return ProviderNameEnum.INVENTORY_ITEM_BY_SLOT.name();
    }

    @Override
    public Class<InventoryItemDto> getDTOClass() {
        return InventoryItemDto.class;
    }
}
