package me.gavin.gavhack.event;

public class PreMoveRotationEvent {
    public float yaw, pitch;

    public PreMoveRotationEvent(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
