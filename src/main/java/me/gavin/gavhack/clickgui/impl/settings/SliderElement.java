package me.gavin.gavhack.clickgui.impl.settings;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.api.AbstractOffsettable;
import me.gavin.gavhack.clickgui.api.IReleaseable;
import me.gavin.gavhack.setting.NumberSetting;
import me.gavin.gavhack.util.Utils;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SliderElement extends AbstractOffsettable implements IReleaseable {
    public NumberSetting parent;
    public SliderElement(NumberSetting setting, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.parent = setting;
    }

    private boolean draggingSlider;
    protected float sliderWidth = 0f;

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        updateSlider(mouseX, mouseY);


        int color = Gavhack.INSTANCE.colorManager.asColor().getRGB();
        Gui.drawRect(x + width - 1, y, x +width, y + height, color);

        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(ChatFormatting.WHITE + parent.name + " " + ChatFormatting.RESET + parent.value, x + 1f, y, color);

        Gui.drawRect(x, y + height - 1, x + (int)sliderWidth, y + height, color);
        if (draggingSlider)
            Gui.drawRect(x, y + height - 1, x + (int)sliderWidth, y + height, 0x50000000);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseInside(mouseX, mouseY) && mouseButton == 0) {
            draggingSlider = true;
            Utils.click();
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            draggingSlider = false;
        }
    }

    private void updateSlider(int mouseX, int mouseY) {
        float difference = Math.min(width, Math.max(0, mouseX - this.x));
        sliderWidth = width * (parent.value - parent.min) / (parent.max - parent.min);
        if (draggingSlider) {
            if (difference == 0) {
                this.parent.setClamped(parent.min);
            } else {
                float newValue = round(difference / width * (parent.max - parent.min) + parent.min, 1);
                parent.setClamped(newValue);
            }
        }
    }

    private float round(float value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
