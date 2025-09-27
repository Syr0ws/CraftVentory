package com.github.syr0ws.craftventory.internal.config.yaml.item;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.action.ClickActionLoader;
import com.github.syr0ws.craftventory.api.config.action.ClickActionLoaderFactory;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.common.util.IdUtil;
import com.github.syr0ws.craftventory.internal.config.SimpleInventoryItemConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YamlInventoryItemLoader {

    private static final String ITEM_KEY = "item";
    private static final String ACTIONS_KEY = "actions";
    private static final String ACTION_KEY = "action";

    private final ClickActionLoaderFactory<ConfigurationMap> factory;
    private final YamlItemStackLoader itemStackLoader = new YamlItemStackLoader();

    public YamlInventoryItemLoader(ClickActionLoaderFactory<ConfigurationMap> factory) {

        if (factory == null) {
            throw new IllegalArgumentException("factory cannot be null");
        }

        this.factory = factory;
    }

    public SimpleInventoryItemConfig loadItem(ConfigurationSection section) throws InventoryConfigException {

        if (section == null) {
            throw new IllegalArgumentException("section cannot be null");
        }

        String id = this.loadId(section);
        ItemStack item = this.loadItemStack(section);
        List<ClickAction> actions = this.loadActions(section);

        return new SimpleInventoryItemConfig(id, item, actions);
    }

    private String loadId(ConfigurationSection section) {
        String id = section.getName();
        return IdUtil.getItemId(id);
    }

    private ItemStack loadItemStack(ConfigurationSection section) throws InventoryConfigException {

        ItemStack item;

        if (section.isItemStack(ITEM_KEY)) {
            item = section.getItemStack(ITEM_KEY);
        } else if (section.isConfigurationSection(ITEM_KEY)) {
            ConfigurationSection itemSection = section.getConfigurationSection(ITEM_KEY);
            item = this.itemStackLoader.loadItem(itemSection);
        } else {
            throw new InventoryConfigException(String.format("Property '%s' missing or invalid at '%s'", ITEM_KEY, section.getCurrentPath()));
        }

        return item;
    }

    private List<ClickAction> loadActions(ConfigurationSection section) throws InventoryConfigException {

        if(!section.isSet(ACTIONS_KEY)) {
            return new ArrayList<>();
        }

        List<Map<?,?>> actionsListMap = section.getMapList(ACTIONS_KEY);
        List<ClickAction> actions = new ArrayList<>();

        for(Map<?,?> map : actionsListMap) {

            ConfigurationMap configurationMap = new ConfigurationMap(section, map);

            if(!configurationMap.isString(ACTION_KEY)) {
                throw new InventoryConfigException("Property '%s' not found or is not a string at '%s'".formatted(ACTION_KEY, configurationMap.getCurrentPath()));
            }

            String clickActionType = configurationMap.getString(ACTION_KEY);
            ClickActionLoader<ConfigurationMap> loader = this.factory.getLoader(clickActionType);

            if (loader == null) {
                throw new InventoryConfigException("Invalid action type '%s' at '%s'".formatted(clickActionType, configurationMap.getCurrentPath()));
            }

            ClickAction action = loader.load(configurationMap);
            actions.add(action);
        }

        return actions;
    }
}
