package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.event.ModeChangeEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerAbilities;

public class Flight extends Module {
    public Flight() {
        super("Flight", "Allows you to fly.", Category.Movement);
        this.settings.add(flightMode);
    }

    public ModeSetting flightMode = new ModeSetting(this, "Mode", "Vanilla", "Vanilla", "Bypass");

    public void onEnable() {
        this.setMetadata(ChatFormatting.WHITE + "[" + flightMode.getMode() + "]" + ChatFormatting.RESET);
        if (flightMode.getMode().equals("Vanilla")) {
            mc.player.capabilities.allowFlying = true;
        }
        else if (flightMode.getMode().equals("Funny")) {
            mc.player.connection.sendPacket(new CPacketPlayer.Rotation());
            mc.player.connection.sendPacket(new CPacketPlayerAbilities());
            mc.player.capabilities.allowFlying = true;
        }
    }

    public void onDisable() {
        mc.player.capabilities.allowFlying = false;
    }

    @Register
    public Listener<ModeChangeEvent> modeChangeListener = event -> {
        if(event.module == this) {
            setMetadata(ChatFormatting.WHITE + "[" + event.newMode + "]" + ChatFormatting.RESET);
        }
    };
}
