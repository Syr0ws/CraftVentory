package com.github.syr0ws.craftventory.api.config.loader.action;

import java.util.Optional;
import java.util.Set;

/**
 * Manages a collection of {@link ClickActionLoader} instances.
 */
public interface ClickActionLoaderManager {

    /**
     * Adds a {@link ClickActionLoader} to the manager.
     *
     * @param loader the loader to add
     */
    void addLoader(ClickActionLoader loader);

    /**
     * Removes a {@link ClickActionLoader} from the manager by its action name.
     *
     * @param actionName the name of the action associated with the loader to remove
     */
    void removeLoader(String actionName);

    /**
     * Checks if a {@link ClickActionLoader} exists for the given action name.
     *
     * @param actionName the name of the action to check
     * @return {@code true} if a loader exists for the given action name, {@code false} otherwise
     */
    boolean hasLoader(String actionName);

    /**
     * Retrieves a {@link ClickActionLoader} by its action name.
     *
     * @param actionName the name of the action associated with the loader to retrieve
     * @return an {@link Optional} containing the loader if found, or an empty {@link Optional} otherwise
     */
    Optional<ClickActionLoader> getLoader(String actionName);

    /**
     * Retrieves all {@link ClickActionLoader} instances managed by this manager.
     *
     * @return a {@link Set} of all loaders
     */
    Set<ClickActionLoader> getLoaders();
}
