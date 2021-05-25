package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerAbilities;
import me.gavin.gavhack.wrapper.Wrapper;

public class Flight extends Module {
    public Flight() {
        super("Flight", "Allows you to fly.", Category.Movement);
        settings.add(flightMode);
    }

    public ModeSetting flightMode = new ModeSetting(this, "Mode", "Vanilla", "Vanilla", "Bypass");

    public void onEnable() {
        if (flightMode.getMode().equals("Vanilla")) {
            mc.player.capabilities.allowFlying = true;
        }
        else if (flightMode.getMode().equals("Funny")) {
            Wrapper.net.sendPacket(new CPacketPlayer.Rotation());
            Wrapper.net.sendPacket(new CPacketPlayerAbilities());
            Wrapper.capabilities.allowFlying = true;
        }
    }

    public void onDisable() {
        mc.player.capabilities.allowFlying = false;
    }
}
