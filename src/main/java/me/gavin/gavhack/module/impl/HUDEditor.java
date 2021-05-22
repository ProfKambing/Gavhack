package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;

public class HUDEditor extends Module {

    public HUDEditor() {
        super("HUDEditor", "Customize your game HUD", Category.Client);
        settings.add(descriptions);
    }

    public final BooleanSetting descriptions = new BooleanSetting(this, "Descriptions", true);

    @Override
    public void onEnable() {
        mc.displayGuiScreen(gavhack.hudEditor);
        disable();
    }
}
