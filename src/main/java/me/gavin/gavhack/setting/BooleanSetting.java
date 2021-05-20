package me.gavin.gavhack.setting;

import me.gavin.gavhack.module.Module;

public class BooleanSetting extends Setting {

    public boolean value;

    public BooleanSetting(Module module, String name, boolean value) {
        super(module, name);
        this.value = value;
    }
}
