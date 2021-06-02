package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.event.PacketEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;

public class Quiver extends Module {
    public Quiver() {
        super("Quiver", "Ching chong quiver :O", Category.Combat);
    }

    @Register
    public final Listener<PacketEvent.Outgoing> packetListener = event -> {
        if(event.getPacket() instanceof CPacketPlayerTryUseItem) {
            if(mc.player.getHeldItemMainhand().getItem() == Items.BOW && mc.player.isHandActive()) {
                mc.player.connection.sendPacket(new CPacketPlayer.Rotation( -90, mc.player.rotationYaw, true));
                mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(mc.player.getActiveHand()));
                mc.player.stopActiveHand();
            }
        }
    };
}
