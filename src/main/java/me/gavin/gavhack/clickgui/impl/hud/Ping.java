package me.gavin.gavhack.clickgui.impl.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;

public class Ping extends HUDComponent {
    public Ping() {
        super("Ping", "Shows your ping", 100, Gavhack.INSTANCE.fontRenderer.getHeight());
    }

    private String ping = "Ping ";

    @Override
    public void drawInHud() {
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(ping, x, y, Gavhack.INSTANCE.colorManager.asColor());
    }

    @Override
    public void onUpdate() {

        int sping = -1;

        if (mc.getConnection() != null || mc.player != null) {
            if (mc.getConnection().getPlayerInfo(mc.player.getUniqueID()) != null)
                sping = mc.getConnection().getPlayerInfo(mc.player.getUniqueID()).getResponseTime();
        }

        ping = "Ping " + ChatFormatting.WHITE + sping;

        this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(ping);

    }
}
