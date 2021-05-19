package me.gavin.gavhack.clickgui.impl.settings;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.api.AbstractOffsettable;
import me.gavin.gavhack.clickgui.api.ITypeable;
import me.gavin.gavhack.setting.KeybindSetting;
import me.gavin.gavhack.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class KeybindElement extends AbstractOffsettable implements ITypeable {

    public KeybindSetting parent;
    private boolean listening;

    public KeybindElement(KeybindSetting bind, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.parent = bind;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseInside(mouseX, mouseY)) {
            listening = !listening;
            Utils.click();
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(x + width - 1, y, x + width, y + height, Gavhack.INSTANCE.colorManager.asColor().getRGB());

        int color = -1;
        int bcolor = Gavhack.INSTANCE.colorManager.asColor().getRGB();

        ChatFormatting cf = ChatFormatting.WHITE;

        if (isMouseInside(mouseX, mouseY)) {
            color = Color.YELLOW.getRGB();
            bcolor = color;
        }

        if (listening) {
            Gavhack.INSTANCE.fontRenderer.drawStringWithShadow("Listening...", x + 1f, y + 1f, color);
        } else {
            Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(
                    cf + parent.name + " <" + ChatFormatting.RESET + Keyboard.getKeyName(parent.bind) + cf + ">",
                    x + 1f, y + 1f, bcolor);

        }
    }

    @Override
    public void keyTyped(char typedChar, int keycode) {
        if (listening) {
            if (keycode == Keyboard.KEY_DELETE) {
                parent.bind = Keyboard.KEY_NONE;
            } else {
                parent.bind = keycode;
            }
            listening = false;
        }
    }
}