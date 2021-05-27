package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.util.wrapper.Wrapper;

public class Disconnect extends Module {

    public Disconnect() {
        super("Disconnect", "Disconnects you from the server.", Category.Misc);
    }

    @Override
    public void onEnable() {
        Wrapper.net.sendPacket(new net.minecraft.network.play.server.SPacketDisconnect());
    }

}
