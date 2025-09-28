package com.github.syr0ws.craftventory.internal.config.loader.action;

import com.github.syr0ws.crafter.util.Validate;
import com.github.syr0ws.craftventory.api.config.action.ClickActionLoader;
import com.github.syr0ws.craftventory.api.config.loader.action.ClickActionLoaderManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SimpleClickActionLoaderManager implements ClickActionLoaderManager {

    private final Map<String, ClickActionLoader> loaders = new HashMap<>();

    @Override
    public void addLoader(ClickActionLoader loader) {
        Validate.notNull(loader, "loader cannot be null");
        this.loaders.put(loader.getName(), loader);
    }

    @Override
    public void removeLoader(String actionName) {
        Validate.notNull(actionName, "actionName cannot be null");
        this.loaders.remove(actionName);
    }

    @Override
    public boolean hasLoader(String actionName) {
        Validate.notNull(actionName, "actionName cannot be null");
        return this.loaders.containsKey(actionName);
    }

    @Override
    public Optional<ClickActionLoader> getLoader(String actionName) {
        Validate.notNull(actionName, "actionName cannot be null");
        return Optional.ofNullable(this.loaders.get(actionName));
    }

    @Override
    public Set<ClickActionLoader> getLoaders() {
        return Set.copyOf(this.loaders.values());
    }
}
