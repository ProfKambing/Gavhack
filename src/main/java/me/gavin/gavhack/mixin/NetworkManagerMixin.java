package me.gavin.gavhack.mixin;

import io.netty.channel.ChannelHandlerContext;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.event.PacketEvent;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class NetworkManagerMixin {

    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    public void sendPacketInject(Packet<?> packetIn, CallbackInfo ci) {
        PacketEvent.Outgoing event = new PacketEvent.Outgoing(packetIn);
        Gavhack.INSTANCE.eventSys.post(event);
        if (event.cancelled)
            ci.cancel();
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void channelRead0Inject(ChannelHandlerContext p_channelRead0_1_, Packet<?> p_channelRead0_2_, CallbackInfo ci) {
        PacketEvent.Incoming event = new PacketEvent.Incoming(p_channelRead0_2_);
        Gavhack.INSTANCE.eventSys.post(event);
        if (event.cancelled)
            ci.cancel();
    }
}
