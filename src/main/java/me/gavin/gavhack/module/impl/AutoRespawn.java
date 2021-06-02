package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;
import me.gavin.gavhack.util.MessageUtil;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Totally not
 * @Skidded from CumHack :)
 * Stuff I Want To Do:
 * Add waypoints
 */

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", "Automatically will respawn you and tell you coords in chat.", Category.Misc);
    }

    public final BooleanSetting respawn = new BooleanSetting(this, "Respawn", true);
    public final BooleanSetting deathCoords = new BooleanSetting(this, "Death Coords", true);
    public final BooleanSetting antiDeathScreen = new BooleanSetting(this, "Anti Death Screen", true);

    @SubscribeEvent
    public void onDisplayDeathScreen(GuiOpenEvent event) {
        if(event.getGui() instanceof GuiGameOver) {
            if(deathCoords.value && event.getGui() instanceof GuiGameOver) {
                if(mc.player.dimension == -1) {
                    MessageUtil.sendMessagePrefix("You died at X: " + mc.player.getPosition().getX() + " Y: " + mc.player.getPosition().getY() + " Z: " + mc.player.getPosition().getZ() + " Dimension: Nether");
                }
                if(mc.player.dimension == 0) {
                    MessageUtil.sendMessagePrefix("You died at X: " + mc.player.getPosition().getX() + " Y: " + mc.player.getPosition().getY() + " Z: " + mc.player.getPosition().getZ() + " Dimension: OverWorld");
                }
                if(mc.player.dimension == 1) {
                    MessageUtil.sendMessagePrefix("You died at X: " + mc.player.getPosition().getX() + " Y: " + mc.player.getPosition().getY() + " Z: " + mc.player.getPosition().getZ() + " Dimension: End");
                }
            }
            if((respawn.value && mc.player.getHealth() <= 0.0f) || (antiDeathScreen.value && mc.player.getHealth() > 0.0f)) {
                event.setCanceled(true);
                mc.player.respawnPlayer();
            }
        }
    }
}
