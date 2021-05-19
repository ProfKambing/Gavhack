package me.gavin.gavhack.clickgui.impl.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;

public class ServerIP extends HUDComponent {
    public ServerIP() {
        super("ServerIP", "The IP of the server that you are on", 40, Gavhack.INSTANCE.fontRenderer.getHeight());
    }

    private String text = "IP ";

    @Override
    public void drawInHud() {
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(text, x, y, Gavhack.INSTANCE.colorManager.asColor());
    }

    @Override
    public void onUpdate() {
        String ip = mc.getCurrentServerData() == null ? "Unknown" : mc.getCurrentServerData().serverIP;
        text = "IP " + ChatFormatting.WHITE + ip;

        this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(text);
    }
}
