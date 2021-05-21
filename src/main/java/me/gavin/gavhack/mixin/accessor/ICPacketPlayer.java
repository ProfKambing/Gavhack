package me.gavin.gavhack.mixin.accessor;

import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CPacketPlayer.class)
public interface ICPacketPlayer {

    @Accessor("yaw")
    void setPacketYaw(float yaw);

    @Accessor("pitch")
    void setPacketPitch(float pitch);
}
