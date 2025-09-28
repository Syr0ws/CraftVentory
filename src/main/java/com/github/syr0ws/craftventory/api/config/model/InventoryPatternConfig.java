package com.github.syr0ws.craftventory.api.config.model;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.inventory.CraftVentoryType;

import java.util.*;

/**
 * Represents the configuration for an inventory pattern. A pattern is a list of strings where each character
 * in the string is a symbol associated with an item or an empty slot in the inventory.
 */
public class InventoryPatternConfig {

    private final CraftVentoryType type;
    private final List<String> pattern;
    private final Map<Character, List<Integer>> symbolSlots;

    /**
     * Constructs an InventoryPatternConfig with the given pattern and inventory type.
     *
     * @param pattern the inventory pattern as a list of strings
     * @param type    the type of inventory
     */
    public InventoryPatternConfig(List<String> pattern, CraftVentoryType type) {
        Validate.notNull(pattern, "pattern cannot be null");
        Validate.notNull(type, "type cannot be null");

        this.type = type;
        this.pattern = List.copyOf(pattern);
        this.symbolSlots = Map.copyOf(this.getSymbolSlots(pattern, type));
    }

    private Map<Character, List<Integer>> getSymbolSlots(List<String> pattern, CraftVentoryType type) {

        Map<Character, List<Integer>> symbolsSlots = new HashMap<>();

        for (int row = 0; row < pattern.size(); row++) {

            String line = pattern.get(row);

            for (int column = 0; column < line.length(); column++) {

                char symbol = line.charAt(column);
                int slot = (row * type.getColumns()) + column;

                if (!symbolsSlots.containsKey(symbol)) {
                    symbolsSlots.put(symbol, new ArrayList<>());
                }

                symbolsSlots.get(symbol).add(slot);
            }
        }

        return symbolsSlots;
    }

    /**
     * Retrieves the symbol at the specified slot in the inventory pattern.
     *
     * @param slot the slot index
     * @return the symbol at the given slot
     * @throws IllegalArgumentException if the slot index is invalid
     */
    public char getSymbolAt(int slot) {

        if(slot < 0 || slot >= this.type.getSize()) {
            throw new IllegalArgumentException("Invalid slot %d".formatted(slot));
        }

        int row = slot / this.type.getColumns();
        int column = slot % this.type.getColumns();

        return this.pattern.get(row).charAt(column);
    }

    /**
     * Returns the inventory pattern as a list of strings.
     *
     * @return the inventory pattern
     */
    public List<String> getPattern() {
        return this.pattern;
    }

    /**
     * Returns a set of unique symbols used in the inventory pattern.
     *
     * @return a set of unique symbols
     */
    public Set<Character> getSymbols() {
        return Set.copyOf(this.symbolSlots.keySet());
    }

    /**
     * Returns a list of slots associated with the given symbol.
     *
     * @param symbol the symbol to look up
     * @return a list of slots for the symbol
     */
    public List<Integer> getSlots(char symbol) {
        return List.copyOf(this.symbolSlots.get(symbol));
    }

    /**
     * Returns a map of symbols to their associated slots.
     *
     * @return a map of symbols with their slots
     */
    public Map<Character, List<Integer>> getSymbolSlots() {
        return this.symbolSlots;
    }
}
