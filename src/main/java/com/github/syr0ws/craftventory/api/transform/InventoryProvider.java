package com.github.syr0ws.craftventory.api.transform;

import com.github.syr0ws.craftventory.api.InventoryService;
import com.github.syr0ws.craftventory.api.config.model.InventoryConfig;
import com.github.syr0ws.craftventory.api.inventory.CraftVentory;
import com.github.syr0ws.craftventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.craftventory.api.transform.i18n.I18n;
import com.github.syr0ws.craftventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.craftventory.api.transform.provider.ProviderManager;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * Represents a provider responsible for supplying a {@link CraftVentory}. Each {@link CraftVentory}
 * must have its own provider.
 */
public interface InventoryProvider {

    /**
     * Creates a new {@link CraftVentory} instance for a specific player.
     *
     * @param service The {@link InventoryService} where the inventory will be stored. Must not be {@code null}.
     * @param player  The {@link Player} who will view the inventory. Must not be {@code null}.
     * @return The newly created {@link CraftVentory} instance for the player.
     * @throws IllegalArgumentException If {@code service} or {@code player} is {@code null}.
     */
    CraftVentory createInventory(InventoryService service, Player player);

    /**
     * Load the configuration of the inventory.
     */
    void loadConfig();

    /**
     * Retrieves the configuration of the inventory.
     *
     * @return The {@link InventoryConfig} instance for this inventory provider.
     */
    InventoryConfig getConfig();

    /**
     * Retrieves the {@link PlaceholderManager} instance associated with this inventory.
     *
     * @return The {@link PlaceholderManager} instance for this inventory provider.
     */
    PlaceholderManager getPlaceholderManager();

    /**
     * Retrieves the {@link ProviderManager} instance associated with this inventory.
     *
     * @return The {@link ProviderManager} instance for this inventory provider.
     */
    ProviderManager getProviderManager();

    /**
     * Retrieves the {@link EnhancementManager} instance associated with this inventory.
     *
     * @return The {@link EnhancementManager} instance for this inventory provider.
     */
    EnhancementManager getEnhancementManager();

    /**
     * Retrieves the unique id of the {@link InventoryProvider}.
     * <p>
     * This id should match the id of the inventory to link the provider with the appropriate inventory.
     * </p>
     *
     * @return The unique id of the {@link InventoryProvider}. Must not be {@code null} or empty.
     */
    String getId();

    /**
     * Retrieves the {@link I18n} instance associated with this inventory, if available.
     * <p>
     * The {@link I18n} instance is used for internationalization and translating texts for the inventory
     * based on the player's locale.
     * </p>
     *
     * @return An {@link Optional} containing the {@link I18n} instance if it has been provided, or an empty {@link Optional}.
     */
    Optional<I18n> getI18n();
}
