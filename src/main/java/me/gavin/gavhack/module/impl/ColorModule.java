package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;
import me.gavin.gavhack.setting.NumberSetting;

public class ColorModule extends Module {

    public final NumberSetting red = new NumberSetting(this, "R", 190f, 0f, 255f, 1f);
    public final NumberSetting green = new NumberSetting(this, "G", 0f, 0f, 255f, 1f);
    public final NumberSetting blue = new NumberSetting(this, "B", 254f, 0f, 255f, 1f);

    public final BooleanSetting rainbow = new BooleanSetting(this, "Rainbow", true);
    public final NumberSetting saturation = new NumberSetting(this, "Rainbow Saturation", 0.7f, 0.1f, 1f, 0.1f);
    public final NumberSetting speed = new NumberSetting(this, "Rainbow Duration", 6f, 1f, 15f, 0.5f);

    public ColorModule() {
        super("Color", "Manage the color of the client", Category.Client);

        this.settings.add(red);
        this.settings.add(green);
        this.settings.add(blue);

        this.settings.add(rainbow);
        this.settings.add(saturation);
        this.settings.add(speed);

        enable();
    }

    @Override
    public void onDisable() {
        enable();
    }
}
