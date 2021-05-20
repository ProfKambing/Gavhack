package me.gavin.gavhack.event;

import me.gavin.gavhack.util.Cancellable;
import net.minecraft.network.Packet;

public class PacketEvent extends Cancellable {

    private final Packet<?> packet;

    private PacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return this.packet;
    }

    public static class Incoming extends PacketEvent {
        public Incoming(Packet<?> packet) {
            super(packet);
        }
    }

    public static class Outgoing extends PacketEvent {
        public Outgoing(Packet<?> packet) {
            super(packet);
        }
    }
}
