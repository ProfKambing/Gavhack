package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import net.minecraft.network.play.client.*;

public class PacketSender extends Module {
    public ModeSetting packetType = new ModeSetting(this, "Packet", "CPacketPlayerAbilities", "CPacketPlayerAbilities", "CPacketKeepAlive", "CPacketPlayer", "CPacketConfirmTransaction", "CPacketAnimation", "CPacketChatMessage", "CPacketPlayerPosition", "CPacketPlayerRotation", "CPacketPlayerPositionRotation");

    public PacketSender() {
        super("PacketSender", "Allows you to send some packets to the server or control your Minecraft client with packets.", Category.Player);
        this.settings.add(packetType);
    }
    
    @Override
    public void onEnable() {
        switch (packetType.getMode()) {
            case "CPacketPlayerAbilities":
                mc.player.connection.sendPacket(new CPacketPlayerAbilities());
            case "CPacketKeepAlive":
                mc.player.connection.sendPacket(new CPacketKeepAlive());
            case "CPacketPlayer":
                mc.player.connection.sendPacket(new CPacketPlayer());
            case "CPacketConfirmTransaction":
                mc.player.connection.sendPacket(new CPacketConfirmTeleport());
            case "CPacketAnimation":
                mc.player.connection.sendPacket(new CPacketAnimation());
            case "CPacketChatMessage":
                mc.player.connection.sendPacket(new CPacketChatMessage());
            case "CPacketPlayerPosition":
                mc.player.connection.sendPacket(new CPacketPlayer.Position());
            case "CPacketPlayerRotation":
                mc.player.connection.sendPacket(new CPacketPlayer.Rotation());
            case "CPacketPlayerPositionRotation":
                mc.player.connection.sendPacket(new CPacketPlayer.PositionRotation());
        }
        toggle();
    }
}
