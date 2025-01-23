package com.github.syr0ws.craftventory.internal.config.yaml.item.property;

import com.github.syr0ws.crafter.text.TextUtil;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class YamlLoreLoader implements ItemPropertyLoader<ConfigurationSection> {

    private static final String LORE_KEY = "lore";

    @Override
    public void loadProperty(ConfigurationSection section, ItemStack item) throws InventoryConfigException {

        if (!section.isList(LORE_KEY)) {
            throw new InventoryConfigException(String.format("Property '%s' is not a list", LORE_KEY));
        }

        List<String> lore = section.getStringList(LORE_KEY);
        lore = TextUtil.parseColors(lore);

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    @Override
    public String getPropertyName() {
        return LORE_KEY;
    }
}
