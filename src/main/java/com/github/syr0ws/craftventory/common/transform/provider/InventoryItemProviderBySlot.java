package com.github.syr0ws.craftventory.common.transform.provider;

import com.github.syr0ws.craftventory.api.config.model.InventoryConfig;
import com.github.syr0ws.craftventory.api.config.model.InventoryContentItemConfig;
import com.github.syr0ws.craftventory.api.config.model.InventoryPatternConfig;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.item.ItemParser;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.transform.dto.InventoryItemDto;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.Optional;

public class InventoryItemProviderBySlot extends AbstractItemProvider<InventoryItemDto> {

    public InventoryItemProviderBySlot(ItemParser itemParser) {
        super(itemParser);
    }

    @Override
    public InventoryItemDto provide(InventoryProvider provider, Context context) {

        // Data retrieval from configuration.
        InventoryConfig config = provider.getConfig();

        Integer slot = context.findData(CommonContextKey.SLOT, Integer.class)
                .orElseThrow(() -> new NullPointerException("No slot found in the context"));

        // Retrieving the symbol associated with the slot.
        InventoryPatternConfig pattern = config.getPattern();
        char symbol = pattern.getSymbolAt(slot);

        // DTO creation and processing.
        Optional<InventoryContentItemConfig> optional = config.getContent().getItemBySymbol(symbol);

        InventoryItemDto dto = optional.map(itemConfig -> {
            ItemStack item = itemConfig.getItem().build();
            return new InventoryItemDto(itemConfig.getItemId(), item, itemConfig.getActions(), Collections.singleton(slot));
        }).orElse(new InventoryItemDto());

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
