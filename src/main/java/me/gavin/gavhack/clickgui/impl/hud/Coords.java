package me.gavin.gavhack.clickgui.impl.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;
import me.gavin.gavhack.module.impl.HUDEditor;

public class Coords extends HUDComponent {
    public Coords() {
        super("Coords", "Shows your coordinates.", 100, Gavhack.INSTANCE.fontRenderer.getHeight());
    }

    private String coords = "Coords: ";

    @Override
    public void drawInHud() {
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(coords, x, y, Gavhack.INSTANCE.colorManager.asColor());
    }

    @Override
    public void onUpdate() {

        String scoords = "";

        int posX = mc.player.getPosition().getX();
        int posY = mc.player.getPosition().getY();
        int posZ = mc.player.getPosition().getZ();

        if (mc.player != null) {
            scoords = posX + " " + posY + " " + posZ;
        }

        coords = "Coords: " + ChatFormatting.WHITE + scoords;

        this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(coords);
    }
}
