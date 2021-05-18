package me.gavin.gavhack.setting;

import java.awt.*;

public class ColorSetting extends Setting {

    public Color color;

    public ColorSetting(String name, Color color) {
        super(name);
        this.color = color;
    }
}
