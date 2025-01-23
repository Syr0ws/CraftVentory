package com.github.syr0ws.craftventory.internal.config.yaml.item.property;

import com.github.syr0ws.crafter.text.TextUtil;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class YamlDisplayNameLoader implements ItemPropertyLoader<ConfigurationSection> {

    private static final String DISPLAY_NAME_KEY = "name";

    @Override
    public void loadProperty(ConfigurationSection section, ItemStack item) throws InventoryConfigException {

        if (!section.isString(DISPLAY_NAME_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' is not a string", DISPLAY_NAME_KEY));
        }

        String name = section.getString(DISPLAY_NAME_KEY);
        name = TextUtil.parseColors(name);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }

    @Override
    public String getPropertyName() {
        return DISPLAY_NAME_KEY;
    }
}
