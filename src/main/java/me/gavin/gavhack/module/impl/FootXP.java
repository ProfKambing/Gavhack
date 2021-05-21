package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.event.PreMoveRotationEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.init.Items;

public class FootXP extends Module {

    public FootXP() {
        super("FootXP", "Looks down server-side when holding and using xp", Category.Combat);
    }

    /*
    private boolean shouldRotate;

    @Register
    public final Listener<TickEvent.PlayerTickEvent> tickListener = event -> {
        shouldRotate = (mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE
                || mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE);
    };

    @Register
    public final Listener<PacketEvent.Outgoing> packetListener = event -> {
        if (shouldRotate) {
            if (event.getPacket() instanceof CPacketPlayer) {
                CPacketPlayer paket = (CPacketPlayer) event.getPacket();
                ((ICPacketPlayer) paket).setPacketPitch(90f);
            }
        }
    };
     */

    @Register
    public final Listener<PreMoveRotationEvent> preRotationEvent = event -> {
        if (mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE
                || mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
            event.pitch = 90f;
        }
    };
}
