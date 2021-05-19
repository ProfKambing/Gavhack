package me.gavin.gavhack.clickgui.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.api.AbstractPanel;
import me.gavin.gavhack.clickgui.api.AbstractPanelComponent;
import me.gavin.gavhack.clickgui.api.ITypeable;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.util.ArrayList;

public class ModulePanel extends AbstractPanel implements ITypeable {

    public Category category;
    private final int count;

    public ModulePanel(Category category, int x, int y, int width, int height, int headerHeight) {
        super(x, y, width, height, headerHeight);
        this.category = category;
        int yOffset = headerHeight;
        ArrayList<Module> mods = Gavhack.INSTANCE.moduleManager.getModulesFromCategory(category);
        count = mods.size();
        for (Module module : mods) {
            ModuleButton button = new ModuleButton(x, y, width, 16, module);
            button.yOffset = yOffset;
            this.buttons.add(button);
            yOffset += 16;
        }
    }

    @Override
    public void handlePanelClick(int mouseX, int mouseY, int mouseButton) {
        for (AbstractPanelComponent button : buttons) {
            button.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(x, y, x + width, y + height, 0x80000000);
        Gui.drawRect(header.x, header.y, header.x + header.width, header.y + header.height, 0x80000000);
        int rgb = Gavhack.INSTANCE.colorManager.asColor().getRGB();
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(category.name() + ChatFormatting.WHITE + " (" + count + ")", x + 4f, y + 3f, rgb);

        int yOffset = header.height;
        for (AbstractPanelComponent button : buttons) {
            button.yOffset = yOffset;
            button.render(mouseX, mouseY, partialTicks);
            yOffset += button.height + button.getSettingHeight();
        }

        updateDragLogic(mouseX, mouseY);
        updatePositionsAndBounds();
    }

    @Override
    public void keyTyped(char typedChar, int keycode) {
        for (AbstractPanelComponent button : buttons) {
            button.keyTyped(typedChar, keycode);
        }
    }
}
