package com.github.syr0ws.craftventory.common.transform.provider;

import com.github.syr0ws.craftventory.api.config.InventoryConfig;
import com.github.syr0ws.craftventory.api.inventory.InventoryViewer;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.craftventory.api.transform.provider.Provider;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.transform.dto.TitleDto;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;
import com.github.syr0ws.craftventory.internal.util.TextUtil;

public class TitleProvider implements Provider<TitleDto> {

    @Override
    public TitleDto provide(InventoryProvider provider, Context context) {

        // Data retrieval from configuration
        InventoryConfig config = provider.getConfig();
        String title = config.getTitle();

        // DTO creation
        TitleDto dto = new TitleDto(title);

        // Enhancement
        EnhancementManager enhancementManager = provider.getEnhancementManager();
        enhancementManager.enhance(dto, TitleDto.class, context);

        // Additional parsing
        String parsed = this.parseTitle(title, provider, context);
        dto.setTitle(parsed);

        return dto;
    }

    @Override
    public String getName() {
        return ProviderNameEnum.TITLE.name();
    }

    @Override
    public Class<TitleDto> getDTOClass() {
        return TitleDto.class;
    }

    private String parseTitle(String title, InventoryProvider provider, Context context) {

        if (!context.hasData(CommonContextKey.VIEWER.name(), InventoryViewer.class)) {
            return TextUtil.parseColors(title);
        }

        InventoryViewer viewer = context.getData(CommonContextKey.VIEWER.name(), InventoryViewer.class);

        String parsed = provider.getI18n()
                .map(i18n -> i18n.getText(viewer, title))
                .orElse(title);

        return TextUtil.parseColors(parsed);
    }
}
