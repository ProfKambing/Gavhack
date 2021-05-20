package me.gavin.gavhack.manager;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.event.PacketEvent;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.network.play.server.SPacketKeepAlive;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.util.math.MathHelper;

public class TpsManager {

    private long prevTime;
    private final float[] ticks = new float[20];
    private int currentTick;

    public TpsManager() {
        Gavhack.INSTANCE.eventSys.subscribe(this);

        this.prevTime = -1;

        for (int i = 0, len = this.ticks.length; i < len; i++) {
            this.ticks[i] = 0.0f;
        }
    }

    public float getTickRate() {
        int tickCount = 0;
        float tickRate = 0.0f;

        for (final float tick : this.ticks) {
            if (tick > 0.0f) {
                tickRate += tick;
                tickCount++;
            }
        }

        return MathHelper.clamp((tickRate / tickCount), 0.0f, 20.0f);
    }

    @Register
    public final Listener<PacketEvent.Incoming> packetListener = event -> {
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            if (this.prevTime != -1) {
                this.ticks[this.currentTick % this.ticks.length] = MathHelper.clamp((20.0f / ((float) (System.currentTimeMillis() - this.prevTime) / 1000.0f)), 0.0f, 20.0f);
                this.currentTick++;
            }

            this.prevTime = System.currentTimeMillis();
        }
    };
}
