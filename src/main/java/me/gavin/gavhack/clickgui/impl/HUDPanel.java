package me.gavin.gavhack.clickgui.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.api.AbstractPanel;
import net.minecraft.client.gui.Gui;

public class HUDPanel extends AbstractPanel<HUDButton> {
    public HUDPanel(int x, int y, int width, int height, int headerHeight) {
        super(x, y, width, height, headerHeight);
    }

    @Override
    protected void handleMouseRelease(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void handlePanelClick(int mouseX, int mouseY, int mouseButton) {
        if (!open)
            return;

        for (HUDButton button : buttons) {
            button.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(header.x, header.y, header.x + header.width, header.y + header.height, 0xCF060606);
        int rgb = Gavhack.INSTANCE.colorManager.asColor().getRGB();
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow("HUD" + ChatFormatting.WHITE + " (" + buttons.size() + ")", x + 1f, y - 0.5f, rgb);

        if (open)
            Gui.drawRect(x, y + header.height, x + width, y + height, 0x90131313);

        updateDragLogic(mouseX, mouseY);
        updatePositionsAndBounds();
    }
}
