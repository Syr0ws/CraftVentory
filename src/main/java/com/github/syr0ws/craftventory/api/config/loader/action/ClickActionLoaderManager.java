package com.github.syr0ws.craftventory.api.config.loader.action;

import com.github.syr0ws.craftventory.api.config.action.ClickActionLoader;

import java.util.Optional;
import java.util.Set;

public interface ClickActionLoaderManager extends Cloneable {

    void addLoader(ClickActionLoader loader);

    void removeLoader(String actionName);

    boolean hasLoader(String actionName);

    Optional<ClickActionLoader> getLoader(String actionName);

    Set<ClickActionLoader> getLoaders();
}
