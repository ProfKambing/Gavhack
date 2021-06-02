package me.gavin.gavhack.clickgui.impl.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;

/**
 * @Created by gerald0mc
 */

public class NetherCoords extends HUDComponent {
    public NetherCoords() {
        super("NetherCoords", "Shows your cords in the nether.", 100, Gavhack.INSTANCE.fontRenderer.getHeight());
    }

    private String coords = "Nether Coords: ";

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

        if(mc.player.dimension == -1) {
            scoords = "[" + posX + "]" + " " + "[" + posY + "]" + " " + "[" + posZ + "]";
        }
        if(mc.player.dimension == 0) {
            scoords = "[" + posX / 8 + "]" + " " + "[" + posY / 8 + "]" + " " + "[" + posZ / 8 + "]";
        }
        if(mc.player.dimension == 1) {
            scoords = "[" + "I" + "]" + " " + "[" + "D" + "]" + " " + "[" + "K" + "]";
        }

        coords = "Nether Coords: " + ChatFormatting.RED + scoords;

        this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(coords);
    }
}
