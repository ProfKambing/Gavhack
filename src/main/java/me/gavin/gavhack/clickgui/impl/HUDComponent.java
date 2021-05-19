package me.gavin.gavhack.clickgui.impl;

import me.gavin.gavhack.clickgui.api.AbstractDragComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public abstract class HUDComponent extends AbstractDragComponent {

    public boolean visible;
    public String name;
    public String description;

    protected final Minecraft mc;

    public HUDComponent(String name, String description, int width, int height) {
        super(10, 10, width, height);
        this.mc = Minecraft.getMinecraft();
        this.name = name;
        this.description = description;
    }

    public abstract void drawInHud();

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            Gui.drawRect(x, y, x + width, y + height, 0x70000000);
            drawInHud();
        }

        updateDragLogic(mouseX, mouseY);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (visible) {
            if (isMouseInside(mouseX, mouseY)) {
                startDragging(mouseX, mouseY);
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (visible) {
            stopDragging();
        }
    }

    public abstract void onUpdate();
}
