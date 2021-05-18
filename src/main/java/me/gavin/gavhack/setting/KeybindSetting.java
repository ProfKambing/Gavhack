package me.gavin.gavhack.setting;

public class KeybindSetting extends Setting {

    public int bind;

    public KeybindSetting(String name, int bind) {
        super(name);
        this.bind = bind;
    }
}
