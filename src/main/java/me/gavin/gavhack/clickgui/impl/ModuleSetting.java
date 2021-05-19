package me.gavin.gavhack.clickgui.impl;

import me.gavin.gavhack.clickgui.api.AbstractComponent;

public abstract class ModuleSetting extends AbstractComponent {

    public int yOffset = 0;

    public ModuleSetting(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);

    @Override
    public abstract void render(int mouseX, int mouseY, float partialTicks);
}
