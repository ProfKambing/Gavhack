package me.gavin.gavhack.util.wrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.PlayerCapabilities;

public class Wrapper {
    public static Minecraft mc = Minecraft.getMinecraft();
    public static NetHandlerPlayClient net = mc.player.connection;
    public static PlayerCapabilities capabilities = mc.player.capabilities;
    public static EntityPlayerSP player = mc.player;
}
