package me.gavin.gavhack.clickgui.impl;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.hud.*;
import me.gavin.gavhack.module.impl.HUDEditor;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.util.ArrayList;

public class HUD extends GuiScreen {

    public ArrayList<HUDComponent> components;
    public final HUDPanel panel;

    public HUD() {
        components = new ArrayList<>();
        panel = new HUDPanel(10, 10, 75, 300, 12);

        // add components here
        this.components.add(new Coords());
        this.components.add(new FPS());
        this.components.add(new Inventory());
        this.components.add(new ModList());
        this.components.add(new NetherCoords());
        this.components.add(new Ping());
        this.components.add(new ServerIP());
        this.components.add(new TPS());
        this.components.add(new Watermark());

        int yOffset = panel.header.height;
        for (HUDComponent component : components) {
            HUDButton button = new HUDButton(component, panel.x, panel.y, panel.width, 12);
            button.yOffset = yOffset;
            panel.buttons.add(button);
            yOffset += 12;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        panel.render(mouseX, mouseY, partialTicks);

        for (HUDComponent component : components) {
            component.render(mouseX, mouseY, partialTicks);
        }

        if (!panel.open)
            return;

        HUDEditor hudEditor = (HUDEditor) Gavhack.INSTANCE.moduleManager.getModule(HUDEditor.class);

        if (!hudEditor.descriptions.value)
            return;

        for (HUDButton button : panel.buttons) {
            if (button.isMouseInside(mouseX, mouseY)) {
                String s = button.parent.description;
                Gui.drawRect(mouseX + 5, mouseY - 10, mouseX + 8 + Gavhack.INSTANCE.fontRenderer.getStringWidth(s), mouseY - 10 + Gavhack.INSTANCE.fontRenderer.getHeight(), 0x80000000);
                Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(s, mouseX + 6f, mouseY - 11f, new Color(-1));
                return;
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        panel.mouseClicked(mouseX, mouseY, mouseButton);

        for (HUDComponent component : components) {
            if (component.isMouseInside(mouseX, mouseY) && mouseButton == 0 && component.visible) {
                component.startDragging(mouseX, mouseY);
                return;
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int moueY, int mouseButton) {
        panel.mouseReleased(mouseX, moueY, mouseButton);

        for (HUDComponent component : components) {
            if (mouseButton == 0) {
                component.stopDragging();
            }
        }
    }

    @Override
    public void updateScreen() {
        panel.onUpdate();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
