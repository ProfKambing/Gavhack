package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.event.PacketEvent;
import me.gavin.gavhack.mixin.accessor.ICPacketPlayer;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;

public class FootXP extends Module {
    public ModeSetting useMode = new ModeSetting(this, "Mode", "Throw", "Throw", "Pitch");

    public FootXP() {
        super("FootXP", "Looks down server-side when holding and using xp", Category.Combat);
    }

    @Register
    public final Listener<PacketEvent.Outgoing> packetListener = event -> {
        if (event.getPacket() instanceof CPacketPlayer) {
            if (mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE
                    || mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE && useMode.getMode().equals("Pitch")) {
                ((ICPacketPlayer) event.getPacket()).setPacketPitch(90f);
            }
            else if (mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE
                    || mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE && useMode.getMode().equals("Throw")) {
                while (mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE
                        || mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
                    ((ICPacketPlayer) event.getPacket()).setPacketPitch(90f);
                    if (mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE) {
                        mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(EnumHand.OFF_HAND)); }
                    }
                    else if (mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
                        mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(EnumHand.OFF_HAND)); }
                    }
                }
            }
        }
    };
}
