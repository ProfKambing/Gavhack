package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import org.lwjgl.opengl.Display;

public class Title extends Module {
    public ModeSetting mode = new ModeSetting(this, "Mode", "Gavhack", "Gavhack", "Ghost");

    public Title() {
        super("Title", "Sets the title of the Minecraft window.", Category.Client);
    }

    @Override
    public void onEnable() {
        if (mode.getMode().equals("Gavhack")) {
            Display.setTitle("> " + Gavhack.MOD_NAME + " " + Gavhack.VERSION);
        }
        else if (mode.getMode().equals("Ghost")) {
            Display.setTitle("Minecraft 1.12.2");
        }
        toggle();
    }
}
