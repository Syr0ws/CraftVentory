package com.github.syr0ws.craftventory.internal.config.loader.action;

import com.github.syr0ws.craftventory.api.config.loader.action.ClickActionLoader;

public enum ActionLoaderEnum {

    CLOSE(new CloseActionLoader()),
    MESSAGE(new MessageActionLoader()),
    PLAYER_COMMAND(new PlayerCommandActionLoader()),
    CONSOLE_COMMAND(new ConsoleCommandActionLoader()),
    PREVIOUS_PAGE(new PreviousPageActionLoader()),
    NEXT_PAGE(new NextPageActionLoader()),
    BROADCAST(new BroadcastActionLoader()),
    SOUND(new SoundActionLoader()),
    UPDATE_CONTENT(new UpdateContentActionLoader()),
    UPDATE_PAGINATION(new UpdatePaginationActionLoader()),
    OPEN_INVENTORY(new OpenInventoryActionLoader()),
    HOME(new HomeActionLoader()),
    BACK(new BackActionLoader()),
    FORWARD(new ForwardActionLoader());

    private final ClickActionLoader loader;

    ActionLoaderEnum(ClickActionLoader loader) {
        this.loader = loader;
    }

    public ClickActionLoader getLoader() {
        return this.loader;
    }
}
