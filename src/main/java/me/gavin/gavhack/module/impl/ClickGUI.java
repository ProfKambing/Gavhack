package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", "Manage modules with a GUI", Category.Client);
        this.keybind.bind = Keyboard.KEY_RSHIFT;
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(gavhack.clickGui);
        disable();
    }
}
