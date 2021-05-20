package me.gavin.gavhack.setting;

import me.gavin.gavhack.module.Module;

public abstract class Setting {

    public final String name;
    public final Module module;

    public Setting(Module module, String name) {
        this.module = module;
        this.name = name;
    }
}
