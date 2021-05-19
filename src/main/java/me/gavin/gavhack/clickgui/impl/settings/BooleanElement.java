package me.gavin.gavhack.clickgui.impl.settings;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.api.AbstractComponent;
import me.gavin.gavhack.clickgui.api.AbstractOffsettable;
import me.gavin.gavhack.clickgui.api.Area;
import me.gavin.gavhack.setting.BooleanSetting;
import me.gavin.gavhack.util.Utils;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class BooleanElement extends AbstractOffsettable {

    public Area button;
    public BooleanSetting parent;

    public BooleanElement(BooleanSetting setting, int x, int y, int width, int height, Area button) {
        super(x, y, width, height);
        this.parent = setting;
        this.button = button;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (button.isMouseInside(mouseX, mouseY)) {
            parent.value = !parent.value;
            Utils.click();
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(x + width - 1, y, x + width, y + height, Gavhack.INSTANCE.colorManager.asColor().getRGB());
        int buttonColor = 0xFF000000;

        if (parent.value)
            buttonColor = Gavhack.INSTANCE.colorManager.asColor().getRGB();

        int color = -1;

        if (isMouseInside(mouseX, mouseY)) {
            color = Color.YELLOW.getRGB();
        }

        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(parent.name, x + 3f + button.width, y + 1f, color);
        Gui.drawRect(button.x, button.y, button.x + button.width, button.y + button.height, buttonColor);

        if (button.isMouseInside(mouseX, mouseY))
            Gui.drawRect(button.x, button.y, button.x + button.width, button.y + button.height, 0x80000000);
    }
}
