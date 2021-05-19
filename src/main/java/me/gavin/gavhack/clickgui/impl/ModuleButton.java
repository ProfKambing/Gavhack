package me.gavin.gavhack.clickgui.impl;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.api.AbstractComponent;
import me.gavin.gavhack.clickgui.api.AbstractOffsettable;
import me.gavin.gavhack.clickgui.api.AbstractPanelComponent;
import me.gavin.gavhack.clickgui.api.Area;
import me.gavin.gavhack.clickgui.impl.settings.BooleanElement;
import me.gavin.gavhack.clickgui.impl.settings.KeybindElement;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;
import me.gavin.gavhack.setting.Setting;
import me.gavin.gavhack.util.Utils;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class ModuleButton extends AbstractPanelComponent {

    public Module parent;
    public boolean open;


    public ModuleButton(int x, int y, int width, int height, Module parent) {
        super(x, y, width, height);
        this.parent = parent;
        int yOffset = height;
        for (Setting s : parent.settings) {
            if (s instanceof BooleanSetting) {
                BooleanElement ele = new BooleanElement((BooleanSetting) s, x, y, width, 14,
                        new Area(x + 3, y + 3, 10, 10));
                ele.yOffset += yOffset;
                this.settingComponents.add(ele);
                yOffset += 14;
            }
        }
        KeybindElement bindEle = new KeybindElement(parent.keybind, x, y, width, 14);
        bindEle.yOffset = yOffset;
        this.settingComponents.add(bindEle);
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

        if (open) {
            for (AbstractOffsettable setting : settingComponents) {
                setting.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        int color = -1;

        if (isMouseInside(mouseX, mouseY))
            color = Color.YELLOW.getRGB();

        if (parent.enabled)
            color = Gavhack.INSTANCE.colorManager.asColor().getRGB();

        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(parent.name, x + 1f, y, color);

        if (open) {
            Gui.drawRect(x + width - 1, y, x + width, y + height, Gavhack.INSTANCE.colorManager.asColor().getRGB());
            int yOffset = this.height;
            for (AbstractOffsettable setting : settingComponents) {
                setting.yOffset = yOffset;
                setting.render(mouseX, mouseY, partialTicks);
                yOffset += setting.height;
            }
        }
    }

    @Override
    public int getSettingHeight() {
        if (this.open) {
            int height = 0;
            for (AbstractComponent setting : settingComponents) {
                height += setting.height;
            }
            return height;
        } else {
            return 0;
        }
    }

    @Override
    public void keyTyped(char typedChar, int keycode) { }

    public void updateChildren() {
        for (AbstractOffsettable setting : settingComponents) {
            setting.x = this.x;
            setting.y = this.y + setting.yOffset;
            if (setting instanceof BooleanElement) {
                ((BooleanElement)setting).button.x = setting.x + 2;
                ((BooleanElement)setting).button.y = setting.y + 2;
            }
        }
    }
}
