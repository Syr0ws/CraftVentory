package com.github.syr0ws.craftventory.internal.inventory.pagination;

import com.github.syr0ws.craftventory.api.config.PaginationConfig;
import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.inventory.InventoryContent;
import com.github.syr0ws.craftventory.api.inventory.data.DataStore;
import com.github.syr0ws.craftventory.api.inventory.exception.InventoryException;
import com.github.syr0ws.craftventory.api.inventory.item.InventoryItem;
import com.github.syr0ws.craftventory.api.inventory.pagination.Pagination;
import com.github.syr0ws.craftventory.api.inventory.pagination.PaginationModel;
import com.github.syr0ws.craftventory.api.transform.InventoryProvider;
import com.github.syr0ws.craftventory.api.util.Context;
import com.github.syr0ws.craftventory.common.inventory.data.CommonDataStoreKey;
import com.github.syr0ws.craftventory.common.transform.dto.InventoryItemDto;
import com.github.syr0ws.craftventory.common.transform.dto.pagination.PaginationItemDto;
import com.github.syr0ws.craftventory.common.transform.provider.ProviderNameEnum;
import com.github.syr0ws.craftventory.common.util.CommonContextKey;
import com.github.syr0ws.craftventory.internal.inventory.item.SimpleInventoryItem;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SimplePagination<T> implements Pagination<T> {

    private final String id;
    private final CraftVentory inventory;
    private final PaginationModel<T> model;
    private final BiFunction<CraftVentory, SimplePagination<T>, List<T>> dataSupplier;
    private final List<Integer> slots;

    public SimplePagination(String id,
                            CraftVentory inventory,
                            PaginationModel<T> model,
                            BiFunction<CraftVentory, SimplePagination<T>, List<T>> dataSupplier,
                            List<Integer> slots) {

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }

        if (inventory == null) {
            throw new IllegalArgumentException("inventory cannot be null");
        }

        if (model == null) {
            throw new IllegalArgumentException("model cannot be null");
        }

        if(dataSupplier == null) {
            throw new IllegalArgumentException("dataSupplier cannot be null");
        }

        if (slots == null) {
            throw new IllegalArgumentException("slots cannot be null");
        }

        this.id = id;
        this.inventory = inventory;
        this.model = model;
        this.dataSupplier = dataSupplier;
        this.slots = slots;
    }

    @Override
    public void update(boolean updateData) {

        if(updateData) {
            this.model.updateItems(this.dataSupplier.apply(this.inventory, this));
        }

        this.updatePaginationItems();
        this.updatePageItems();
    }

    private void updatePaginationItems() {

        InventoryProvider provider = this.inventory.getProvider();
        InventoryContent content = this.inventory.getContent();

        List<T> paginatedItems = this.model.getCurrentItems();

        int i = 0;

        for (int slot : this.slots) {

            InventoryItem inventoryItem = null;

            // Checking whether there is a data associated with the current slot or not.
            // This might not be the case if there is fewer paginated data than slots.
            if (i < paginatedItems.size()) {

                T paginatedItem = paginatedItems.get(i);

                // Building the paginated item.
                Context context = this.getPaginationContext();
                context.addData(CommonContextKey.SLOT, slot, Integer.class);
                context.addData(CommonContextKey.PAGINATED_DATA, paginatedItem, this.model.getDataType());

                PaginationItemDto dto = provider.getProviderManager()
                        .provide(ProviderNameEnum.PAGINATION_ITEM.name(), PaginationItemDto.class, provider, context)
                        .orElseThrow(() -> new InventoryException(String.format("Cannot provide pagination item for pagination '%s'", this.id)));

                inventoryItem = new SimpleInventoryItem(dto.getId(), dto.getItem(), dto.getActions());

                // Associating the paginated data with the item.
                DataStore store = inventoryItem.getLocalStore();
                store.setData(CommonDataStoreKey.PAGINATED_DATA, paginatedItem, this.model.getDataType());
            }

            // Item may be null if there is no data or if it has been set to null during the transformation process.
            if (inventoryItem == null) {
                content.removeItem(slot);
            } else {
                content.setItem(inventoryItem, slot);
            }

            i++;
        }
    }

    private void updatePageItems() {

        InventoryProvider provider = this.inventory.getProvider();
        InventoryContent content = this.inventory.getContent();

        Context context = this.getPaginationContext();

        PaginationConfig paginationConfig = provider.getConfig()
                .getPaginationConfig(this.id)
                .orElseThrow(() -> new InventoryException(String.format("Cannot update page items: no pagination with id '%s' found in the configuration", this.id)));

        // Previous page item handling.
        if (this.model.hasPreviousPage()) {

            provider.getProviderManager()
                    .provide(ProviderNameEnum.PAGINATION_PREVIOUS_PAGE_ITEM.name(), InventoryItemDto.class, provider, context)
                    .filter(dto -> dto.getItemId() != null && dto.getItem() != null)
                    .ifPresent(dto -> {
                        InventoryItem item = new SimpleInventoryItem(dto.getItemId(), dto.getItem(), dto.getActions());
                        content.setItem(item, dto.getSlots());
                    });

        } else {
            content.removeItems(paginationConfig.getPreviousPageItemSlots());
        }

        // Next page item handling.
        if (this.model.hasNextPage()) {

            provider.getProviderManager()
                    .provide(ProviderNameEnum.PAGINATION_NEXT_PAGE_ITEM.name(), InventoryItemDto.class, provider, context)
                    .filter(dto -> dto.getItemId() != null && dto.getItem() != null)
                    .ifPresent(dto -> {
                        InventoryItem item = new SimpleInventoryItem(dto.getItemId(), dto.getItem(), dto.getActions());
                        content.setItem(item, dto.getSlots());
                    });
        } else {
            content.removeItems(paginationConfig.getNextPageItemSlots());
        }
    }

    @Override
    public void previousPage() {

        if (this.model.hasPreviousPage()) {
            this.model.previousPage();
            this.update(false);
        }
    }

    @Override
    public void nextPage() {

        if (this.model.hasNextPage()) {
            this.model.nextPage();
            this.update(false);
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public PaginationModel<T> getModel() {
        return this.model;
    }

    @Override
    public List<Integer> getSlots() {
        return Collections.unmodifiableList(this.slots);
    }

    private Context getPaginationContext() {
        Context context = this.inventory.getDefaultContext();
        context.addData(CommonContextKey.PAGINATION_ID, this.id, String.class);
        return context;
    }
}
