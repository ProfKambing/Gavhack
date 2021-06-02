package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.event.ModeChangeEvent;
import me.gavin.gavhack.event.PacketEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.gavhack.util.MSTimer;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;

public class Criticals extends Module {

    private final ModeSetting mode = new ModeSetting(this, "Mode", "Packet", "Packet", "2b2t", "Bypass");

    private final MSTimer timer = new MSTimer();

    public Criticals() {
        super("Criticals", "Hit criticals (almost) every time", Category.Combat);
        this.settings.add(mode);
    }

    @Override
    public void onEnable() {
        this.setMetadata(ChatFormatting.WHITE + "[" + this.mode.getMode() + "]" + ChatFormatting.RESET);
    }

    @Register
    public final Listener<PacketEvent.Outgoing> packetListener = event -> {
        if (event.getPacket() instanceof CPacketUseEntity) {
            CPacketUseEntity packet = (CPacketUseEntity) event.getPacket();
            if (packet.getAction() == CPacketUseEntity.Action.ATTACK && mc.player.onGround) {
                if (mode.getMode().equals("Packet")) {
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1f, mc.player.posZ, false));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
                } else if (mode.getMode().equals("2b2t")) {
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.11, mc.player.posZ, false));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.11, mc.player.posZ, false));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1100013579, mc.player.posZ, false));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1100013579, mc.player.posZ, false));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1100013579, mc.player.posZ, false));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1100013579, mc.player.posZ, false));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
                } else {
                    if (this.mc.player.fallDistance > 0.0f) {
                        return;
                    } if (this.mc.player.isInLava() || this.mc.player.isInWater()) {
                        return;
                    } if (this.timer.passed(1000)) {
                        this.timer.reset();
                        this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.11, this.mc.player.posZ, false));
                        this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.1100013579, this.mc.player.posZ, false));
                        this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.3579E-6, this.mc.player.posZ, false));
                    }
                }
            }
        }
    };

    @Register
    public final Listener<ModeChangeEvent> modeChangeEventListener = event -> {
        if (event.module == this) {
            this.setMetadata(ChatFormatting.WHITE + "[" + this.mode.getMode() + "]" + ChatFormatting.RESET);
        }
    };
}
