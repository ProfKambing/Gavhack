package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.text.TextComponentString;

public class FakeDC extends Module {
    public FakeDC() {
        super("FakeDC", "Clip you getting kicked in case of emergency.", Category.Misc);
    }

    @Override
    public void onEnable() {
        mc.getConnection().handleDisconnect(new SPacketDisconnect(new TextComponentString("Internal Exception: java.lang.NullPointerException")));
        toggle();
    }
}
