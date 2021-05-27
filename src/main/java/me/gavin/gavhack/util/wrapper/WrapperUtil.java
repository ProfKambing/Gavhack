package me.gavin.gavhack.util.wrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;

public class WrapperUtil {
    public static void TryUseItemLeft() { Minecraft.getMinecraft().player.connection.sendPacket(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND)); }
    public static void TryUseItemRight() { Minecraft.getMinecraft().player.connection.sendPacket(new CPacketPlayerTryUseItem(EnumHand.OFF_HAND)); }
}
