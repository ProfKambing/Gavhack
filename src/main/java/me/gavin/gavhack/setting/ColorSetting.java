package me.gavin.gavhack.setting;

import me.gavin.gavhack.module.Module;

import java.awt.*;

public class ColorSetting extends Setting {

    public Color value;

    public ColorSetting(Module module, String name, Color color) {
        super(module, name);
    }
}
