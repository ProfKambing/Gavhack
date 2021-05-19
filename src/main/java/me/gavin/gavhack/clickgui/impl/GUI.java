package me.gavin.gavhack.clickgui.impl;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.impl.ClickGUI;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class GUI extends GuiScreen {

    public ArrayList<ModulePanel> panels;

    public GUI() {
        panels = new ArrayList<>();

        int xOff = 10;
        for (Category c : Category.values()) {
            panels.add(new ModulePanel(c, xOff, 10, 75, 300, 12));
            xOff += 85;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        final ClickGUI clickgui = (ClickGUI)Gavhack.INSTANCE.moduleManager.getModule(ClickGUI.class);
        if (clickgui.background.value)
            drawDefaultBackground();

        for (ModulePanel panel : panels) {
            panel.render(mouseX, mouseY, partialTicks);
        }

        if (!(clickgui.descriptions.value))
            return;

        for (ModulePanel panel : panels) {
            if (!panel.open)
                continue;
            for (ModuleButton button : panel.buttons) {
                if (button.isMouseInside(mouseX, mouseY)) {
                    String s = button.parent.description;
                    Gui.drawRect(mouseX + 5, mouseY - 10, mouseX + 8 + (int) Gavhack.INSTANCE.fontRenderer.getStringWidth(s), mouseY - 10 + (int)Gavhack.INSTANCE.fontRenderer.FONT_HEIGHT, 0x80000000);
                    Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(s, mouseX + 6f, mouseY - 11f, -1);
                    return;
                }
            }
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
