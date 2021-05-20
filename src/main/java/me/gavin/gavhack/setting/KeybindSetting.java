package me.gavin.gavhack.setting;

import me.gavin.gavhack.module.Module;

public class KeybindSetting extends Setting {

    public int bind;

    public KeybindSetting(Module module, String name, int bind) {
        super(module, name);
        this.bind = bind;
    }
}
