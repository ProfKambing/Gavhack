package me.gavin.gavhack.mixin;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.event.PreMoveRotationEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class EntityPlayerSPMixin {

    @Shadow
    public float rotationYaw;

    @Shadow
    public float rotationPitch;

    private float prevYaw;
    private float prevPitch;

    @Inject(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;onUpdateWalkingPlayer()V", shift = At.Shift.BEFORE))
    public void onUpdatePreInject(CallbackInfo ci) {
        PreMoveRotationEvent event = new PreMoveRotationEvent(rotationYaw, rotationPitch);
        Gavhack.INSTANCE.eventSys.post(event);

        this.prevYaw = rotationYaw;
        this.prevPitch = rotationPitch;

        rotationYaw = event.yaw;
        rotationPitch = event.pitch;
    }

    @Inject(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;onUpdateWalkingPlayer()V", shift = At.Shift.AFTER))
    public void onUpdatePostInject(CallbackInfo ci) {
        rotationPitch = prevPitch;
        rotationYaw = prevYaw;
    }
}
