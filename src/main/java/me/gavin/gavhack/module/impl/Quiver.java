package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.event.PacketEvent;
import me.gavin.gavhack.mixin.accessor.ICPacketPlayer;
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
        if(event.getPacket() instanceof CPacketPlayer) {
            if(mc.player.getHeldItemMainhand().getItem() == Items.BOW && mc.player.isHandActive()) {
                ((ICPacketPlayer) event.getPacket()).setPacketPitch(-90f);
                mc.player.stopActiveHand();
            }
        }
    };
}
