package me.gavin.gavhack.clickgui.impl.settings;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.api.AbstractOffsettable;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.gavhack.util.Utils;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class ModeElement extends AbstractOffsettable {

    public ModeSetting parent;
    public ModeElement(ModeSetting setting, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.parent = setting;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

        if (isMouseInside(mouseX, mouseY)) {
            if (mouseButton == 0) {
                parent.cycle(false);
            } else if (mouseButton == 1) {
                parent.cycle(true);
            }
            Utils.click();
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        int color = Gavhack.INSTANCE.colorManager.asColor().getRGB();
        Gui.drawRect(x + width - 1, y, x + width, y + height, color);

        if (isMouseInside(mouseX, mouseY))
            color = Color.YELLOW.getRGB();

        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(ChatFormatting.WHITE + parent.name + ": " + ChatFormatting.RESET + parent.getMode(), x + 1f, y + 1f, new Color(color));
    }
}
