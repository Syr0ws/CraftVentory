package com.github.syr0ws.fastinventory.common.transform.placeholder.pagination;

import com.github.syr0ws.fastinventory.api.inventory.pagination.PaginationModel;
import com.github.syr0ws.fastinventory.api.util.Context;

public class TotalPagesPlaceholder extends PaginationPlaceholder {

    @Override
    public String getName() {
        return "%total_pages%";
    }

    @Override
    public String getValue(Context context) {
        PaginationModel<?> model = super.getPaginationModel(context);
        return Integer.toString(model.countPages());
    }
}