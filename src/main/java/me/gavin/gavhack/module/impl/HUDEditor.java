package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;

public class HUDEditor extends Module {

    public HUDEditor() {
        super("HUDEditor", "Customize your game HUD", Category.Client);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(gavhack.hudEditor);
        disable();
    }
}
