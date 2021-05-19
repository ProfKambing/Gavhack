package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Module {

    public final BooleanSetting descriptions = new BooleanSetting("Descriptions", true);
    public final BooleanSetting background = new BooleanSetting("Background", false);

    public ClickGUI() {
        super("ClickGUI", "Manage modules with a GUI", Category.Client);
        this.keybind.bind = Keyboard.KEY_RSHIFT;
        this.settings.add(descriptions);
        this.settings.add(background);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(gavhack.clickGui);
        disable();
    }
}
