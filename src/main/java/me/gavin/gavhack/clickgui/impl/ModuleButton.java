package me.gavin.gavhack.clickgui.impl;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.api.AbstractPanelComponent;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.util.Utils;

import java.awt.*;

public class ModuleButton extends AbstractPanelComponent {

    public Module parent;
    public boolean open;

    public ModuleButton(int x, int y, int width, int height, Module parent) {
        super(x, y, width, height);
        this.parent = parent;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseInside(mouseX, mouseY)) {
            if (mouseButton == 0) {
                parent.toggle();
            } else if (mouseButton == 1) {
                open = !open;
            }
            Utils.click();
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        int color = -1;

        if (parent.enabled)
            color = Gavhack.INSTANCE.colorManager.asColor().getRGB();

        if (isMouseInside(mouseX, mouseY))
            color = Color.YELLOW.getRGB();

        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(parent.name, x + 1f, y, color);
    }

    @Override
    public int getSettingHeight() {
        return 0;
    }

    @Override
    public void keyTyped(char typedChar, int keycode) { }
}
