package me.gavin.gavhack.clickgui.impl;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.api.AbstractPanelComponent;

import java.awt.*;

public class HUDButton extends AbstractPanelComponent {

    public HUDComponent parent;

    public HUDButton(HUDComponent parent, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.parent = parent;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseInside(mouseX, mouseY)) {
            parent.visible = !parent.visible;
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        int color = -1;

        if (isMouseInside(mouseX, mouseY))
            color = Color.YELLOW.getRGB();

        if (parent.visible)
            color = Gavhack.INSTANCE.colorManager.asColor().getRGB();

        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(parent.name, x + 1f, y + 1f, color);
    }

    @Override
    public int getSettingHeight() {
        return 0;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void keyTyped(char typedChar, int keycode) {

    }
}
