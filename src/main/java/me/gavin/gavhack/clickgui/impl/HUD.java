package me.gavin.gavhack.clickgui.impl;

import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;

public class HUD extends GuiScreen {

    public ArrayList<HUDComponent> components;
    public final HUDPanel panel;

    public HUD() {
        components = new ArrayList<>();
        panel = new HUDPanel(10, 10, 75, 300, 12);

        // add components here

        for (HUDComponent component : components) {

        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        panel.render(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        panel.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(int mouseX, int moueY, int mouseButton) {
        panel.mouseReleased(mouseX, moueY, mouseButton);
    }
}
