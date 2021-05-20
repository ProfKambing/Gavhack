package me.gavin.gavhack.clickgui.impl.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;

public class TPS extends HUDComponent {
    public TPS() {
        super("TPS", "Shows server TPS", Gavhack.INSTANCE.fontRenderer.getStringWidth("TPS "), Gavhack.INSTANCE.fontRenderer.getHeight());
    }

    private String text = "TPS ";

    @Override
    public void drawInHud() {
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(text, x, y, Gavhack.INSTANCE.colorManager.asColor());
    }

    @Override
    public void onUpdate() {
        text = "TPS " + ChatFormatting.WHITE + String.format("%.2f", Gavhack.INSTANCE.tpsManager.getTickRate());
        this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(text);
    }
}
