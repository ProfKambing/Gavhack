package me.gavin.gavhack.clickgui.impl.hud;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;
import me.gavin.gavhack.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Inventory extends HUDComponent {
    public Inventory() {
        super("Inventory", "Shows your inventory", 162, 54);
    }

    private boolean shouldDrawRect;

    @Override
    public void drawInHud() {
        if (mc.currentScreen != Gavhack.INSTANCE.hudEditor)
            Gui.drawRect(x, y, x + width, y + height, 0x70000000);

        NonNullList<ItemStack> items = Minecraft.getMinecraft().player.inventory.mainInventory;
        for (int size = items.size(), item = 9; item < size; ++item) {
            int slotX = x + 1 + item % 9 * 18;
            int slotY = y + 1 + (item / 9 - 1) * 18;
            RenderUtil.renderItemStack(items.get(item), slotX,slotY);
        }
    }

    @Override
    public void onUpdate() { }
}
