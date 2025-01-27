package me.gavin.gavhack.clickgui.impl.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;

public class Watermark extends HUDComponent {

    private final String text;

    public Watermark() {
        super("Watermark", "Draws a watermark", 50, Gavhack.INSTANCE.fontRenderer.getHeight());
        text = Gavhack.MOD_NAME + " " + ChatFormatting.WHITE + Gavhack.VERSION;
    }

    @Override
    public void drawInHud() {
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(text, x, y, Gavhack.INSTANCE.colorManager.asColor());
    }

    @Override
    public void onUpdate() {
        this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(text);
        this.height = Gavhack.INSTANCE.fontRenderer.getHeight() + 1;
    }
}
