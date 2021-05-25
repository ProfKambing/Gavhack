package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.event.PacketEvent;
import me.gavin.gavhack.mixin.accessor.ICPacketPlayer;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayer;

public class FootXP extends Module {

    public FootXP() {
        super("FootXP", "Looks down server-side when holding and using xp", Category.Combat);
    }

    @Register
    public final Listener<PacketEvent.Outgoing> packetListener = event -> {
        if (event.getPacket() instanceof CPacketPlayer) {
            if (mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE
                    || mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
                ((ICPacketPlayer) event.getPacket()).setPacketPitch(90f);
            }
        }
    };
}
