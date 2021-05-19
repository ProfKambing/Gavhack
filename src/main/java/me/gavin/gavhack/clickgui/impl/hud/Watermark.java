package me.gavin.gavhack.clickgui.impl.hud;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;

public class Watermark extends HUDComponent {

    private final String text;

    public Watermark() {
        super("Watermark", "Draws a watermark", 50, 14);
        text = Gavhack.MOD_NAME + " " + Gavhack.VERSION;
    }

    @Override
    public void drawInHud() {
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(text, x + 2, y + 1, Gavhack.INSTANCE.colorManager.asColor().getRGB());
    }

    @Override
    public void onUpdate() {
        this.width = mc.fontRenderer.getStringWidth(text);
    }
}
