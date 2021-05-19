package me.gavin.gavhack.clickgui.impl;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.impl.ClickGUI;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class GUI extends GuiScreen {

    public ArrayList<ModulePanel> panels;

    public GUI() {
        panels = new ArrayList<>();

        int xOff = 10;
        for (Category c : Category.values()) {
            panels.add(new ModulePanel(c, xOff, 10, 100, 300, 14));
            xOff += 110;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (ModulePanel panel : panels) {
            panel.render(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (ModulePanel panel : panels) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        for (ModulePanel panel : panels) {
            panel.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void keyTyped(char keyChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
            Gavhack.INSTANCE.moduleManager.getModule(ClickGUI.class).disable();
        }

        for (ModulePanel panel : panels) {
            panel.keyTyped(keyChar, keyCode);
        }
    }

    @Override
    public boolean doesGuiPauseGame() { return false; }
}
