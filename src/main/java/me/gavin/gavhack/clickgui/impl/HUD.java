package me.gavin.gavhack.clickgui.impl;

import me.gavin.gavhack.clickgui.impl.hud.Watermark;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;

public class HUD extends GuiScreen {

    public ArrayList<HUDComponent> components;
    public final HUDPanel panel;

    public HUD() {
        components = new ArrayList<>();
        panel = new HUDPanel(10, 10, 75, 300, 12);

        // add components here
        this.components.add(new Watermark());

        int yOffset = panel.header.height;
        for (HUDComponent component : components) {
            HUDButton button = new HUDButton(component, panel.x, panel.y, panel.width, 14);
            button.yOffset = yOffset;
            panel.buttons.add(button);
            yOffset += 14;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        panel.render(mouseX, mouseY, partialTicks);

        for (HUDComponent component : components) {
            component.render(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        panel.mouseClicked(mouseX, mouseY, mouseButton);

        for (HUDComponent component : components) {
            if (component.isMouseInside(mouseX, mouseY) && mouseButton == 0) {
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
}
