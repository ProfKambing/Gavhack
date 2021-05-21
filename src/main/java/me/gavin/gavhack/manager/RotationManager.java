package me.gavin.gavhack.manager;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.event.PacketEvent;
import me.gavin.quasar.Listener;
import net.minecraft.network.play.client.CPacketPlayer;

// coom
public class RotationManager {

    private float yaw, pitch;

    private boolean rotating;

    public final Listener<PacketEvent.Outgoing> listener = event -> {
        if (rotating) {
            if (event.getPacket() instanceof CPacketPlayer) {

            }
        }
    };

    public RotationManager() {
        Gavhack.INSTANCE.eventSys.subscribe(listener);
    }

    public boolean isRotating() {
        return this.rotating;
    }

    public void setRotating(boolean flag) {
        this.rotating = flag;
    }

    public void setRotations(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
