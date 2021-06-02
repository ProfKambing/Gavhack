package me.gavin.gavhack.clickgui.impl.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;
import net.minecraft.client.Minecraft;

public class FPS extends HUDComponent {
    public FPS() {
        super("FPS", "Shows your frame rate", 1, Gavhack.INSTANCE.fontRenderer.getHeight());
    }

    private String text = "FPS ";

    @Override
    public void drawInHud() {
        text = "FPS " + ChatFormatting.WHITE + Minecraft.getDebugFPS();
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(text, x, y, Gavhack.INSTANCE.colorManager.asColor());
    }

    @Override
    public void onUpdate() {
        this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(text);
    }
}
