package com.github.syr0ws.craftventory.common.inventory.action;

import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.api.inventory.event.CraftVentoryClickEvent;
import com.github.syr0ws.craftventory.api.inventory.pagination.Pagination;

import java.util.Set;

public class NextPageAction extends CommonAction {

    public static final String ACTION_NAME = "NEXT_PAGE";

    private final String paginationId;

    public NextPageAction(Set<ClickType> clickTypes, String paginationId) {
        super(clickTypes);

        if (paginationId == null || paginationId.isEmpty()) {
            throw new IllegalArgumentException("paginationId cannot be null or empty");
        }

        this.paginationId = paginationId;
    }

    @Override
    public void execute(CraftVentoryClickEvent event) {

        CraftVentory inventory = event.getInventory();

        Pagination<?> pagination = inventory.getPaginationManager()
                .getPagination(this.paginationId)
                .orElseThrow(() -> new NullPointerException(String.format("No pagination found with id %s", this.paginationId)));

        pagination.nextPage();

        // TODO When updating the view, other items that display pagination data will not be updated.
        inventory.updateView();
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }
}
