package com.github.syr0ws.fastinventory.internal.inventory.history;

import com.github.syr0ws.fastinventory.api.inventory.FastInventory;
import com.github.syr0ws.fastinventory.api.inventory.InventoryHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleInventoryHistory implements InventoryHistory {

    private final List<FastInventory> history = new ArrayList<>();
    private int index = -1;
    private boolean actionInProgress;

    @Override
    public void open(FastInventory inventory) {
        this.open(inventory, true);
    }

    @Override
    public void open(FastInventory inventory, boolean newHistory) {

        if(inventory == null) {
            throw new IllegalArgumentException("inventory cannot be null");
        }

        if(newHistory) {
            this.close();
        }

        this.history.add(inventory);
        this.index++;

        inventory.open();
    }

    @Override
    public void close() {

        // Close the currently opened inventory.
        this.history.get(this.index).close();

        // Reset the history.
        this.index = -1;
        this.history.clear();
    }

    @Override
    public void home() {

        // Check that the home inventory is not already opened.
        if(this.index == 0) {
            return;
        }

        this.closeCurrentInventory();

        // Open the home inventory.
        this.index = 0;
        this.history.get(this.index).open();
    }

    @Override
    public void backward() {

        if(this.hasBackward()) {

            this.closeCurrentInventory();

            FastInventory inventory = this.history.get(--this.index);
            inventory.open();
        }
    }

    @Override
    public void backward(String inventoryId) {

        if(inventoryId == null) {
            throw new IllegalArgumentException("inventoryId cannot be null");
        }

        for(int i = 0; i < this.index; i++) {

            FastInventory inventory = this.history.get(i);

            if(!inventoryId.equals(inventory.getId())) {
                continue;
            }

            this.closeCurrentInventory();

            inventory.open();
            this.index = i;

            break;
        }
    }

    @Override
    public boolean hasBackward() {
        return this.index > 0;
    }

    @Override
    public void forward() {

        if(this.hasForward()) {

            this.closeCurrentInventory();

            FastInventory inventory = this.history.get(++this.index);
            inventory.open();
        }
    }

    @Override
    public void forward(String inventoryId) {

        if(inventoryId == null) {
            throw new IllegalArgumentException("inventoryId cannot be null");
        }

        for(int i = this.index + 1; i < this.history.size(); i++) {

            FastInventory inventory = this.history.get(i);

            if(!inventoryId.equals(inventory.getId())) {
                continue;
            }

            this.closeCurrentInventory();

            inventory.open();
            this.index = i;

            break;
        }
    }

    @Override
    public boolean hasForward() {
        return this.index < this.history.size() - 1;
    }

    @Override
    public boolean hasActionInProgress() {
        return this.actionInProgress;
    }

    @Override
    public boolean contains(String inventoryId) {

        if(inventoryId == null) {
            throw new IllegalArgumentException("inventoryId cannot be null");
        }

        return this.history.stream().anyMatch(inventory -> inventory.getId().equals(inventoryId));
    }

    @Override
    public boolean isEmpty() {
        return this.history.isEmpty();
    }

    @Override
    public boolean hasOpenedInventory() {
        return !this.hasActionInProgress() && this.index >= 0;
    }

    @Override
    public Optional<FastInventory> getOpenedInventory() {
        return this.hasOpenedInventory() ? Optional.of(this.history.get(this.index)) : Optional.empty();
    }

    private void closeCurrentInventory() {
        this.actionInProgress = true;
        this.history.get(this.index).close();
        this.actionInProgress = false;
    }
}
