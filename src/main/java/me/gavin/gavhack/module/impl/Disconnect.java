package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;

public class Disconnect extends Module {

    public Disconnect() {
        super("Disconnect", "Disconnects you from the server.", Category.Misc);
    }

    @Override
    public void onEnable() {
        mc.player.connection.sendPacket(new net.minecraft.network.play.server.SPacketDisconnect());
    }

}
