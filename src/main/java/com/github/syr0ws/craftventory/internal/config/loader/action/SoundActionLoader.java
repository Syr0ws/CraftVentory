package com.github.syr0ws.craftventory.internal.config.loader.action;

import com.github.syr0ws.crafter.config.ConfigurationMap;
import com.github.syr0ws.craftventory.api.config.exception.InventoryConfigException;
import com.github.syr0ws.craftventory.api.inventory.action.ClickAction;
import com.github.syr0ws.craftventory.api.inventory.action.ClickType;
import com.github.syr0ws.craftventory.common.config.yaml.YamlCommonActionLoader;
import com.github.syr0ws.craftventory.common.inventory.action.SoundAction;
import org.bukkit.Sound;

import java.util.Set;

public class SoundActionLoader extends YamlCommonActionLoader {

    private static final String SOUND_KEY = "sound";
    private static final String VOLUME_KEY = "volume";
    private static final String PITCH_KEY = "pitch";

    @Override
    public ClickAction load(ConfigurationMap map) throws InventoryConfigException {

        Set<ClickType> clickTypes = super.loadClickTypes(map);
        Sound sound = this.loadSound(map);
        float volume = this.loadVolume(map);
        float pitch = this.loadPitch(map);

        return new SoundAction(clickTypes, sound, volume, pitch);
    }

    private Sound loadSound(ConfigurationMap map) throws InventoryConfigException {

        if (!map.isString(SOUND_KEY)) {
            throw new InventoryConfigException("Property '%s' not found or is not a string for action '%s' at '%s'".formatted(SOUND_KEY, this.getName(), map.getCurrentPath()));
        }

        String soundName = map.getString(SOUND_KEY);

        try {
            return Sound.valueOf(soundName);
        } catch (IllegalArgumentException exception) {
            throw new InventoryConfigException("Invalid sound '%s' for action '%s' at '%s'".formatted(soundName, this.getName(), map.getCurrentPath()));
        }
    }

    private float loadVolume(ConfigurationMap map) throws InventoryConfigException {

        if (!map.isDouble(VOLUME_KEY)) {
            throw new InventoryConfigException("Property '%s' not found or is not a double for action '%s' at '%s'".formatted(VOLUME_KEY, this.getName(), map.getCurrentPath()));
        }

        float volume = (float) map.getDouble(VOLUME_KEY);

        if (volume < 0f) {
            throw new InventoryConfigException("Volume cannot be negative for action '%s' at '%s'".formatted(this.getName(), map.getCurrentPath()));
        }

        return volume;
    }

    private float loadPitch(ConfigurationMap map) throws InventoryConfigException {

        if (!map.isDouble(PITCH_KEY)) {
            throw new InventoryConfigException("Property '%s' not found or is not a double for action '%s' at '%s'".formatted(PITCH_KEY, this.getName(), map.getCurrentPath()));
        }

        float pitch = (float) map.getDouble(PITCH_KEY);

        if (pitch < 0f || pitch > 2f) {
            throw new InventoryConfigException("Pitch cannot be negative or greater than 2 for action '%s' at '%s'".formatted(this.getName(), map.getCurrentPath()));
        }

        return pitch;
    }

    @Override
    public String getName() {
        return SoundAction.ACTION_NAME;
    }
}
