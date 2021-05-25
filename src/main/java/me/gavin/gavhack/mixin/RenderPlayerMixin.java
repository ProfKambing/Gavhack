package me.gavin.gavhack.mixin;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.module.impl.Nametags;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public class RenderPlayerMixin {

    @Inject(method = "renderEntityName", at = @At("HEAD"), cancellable = true)
    public void renderEntityNameInject(AbstractClientPlayer entityIn, double x, double y, double z, String name, double distanceSq, CallbackInfo ci) {
        if (Gavhack.INSTANCE.moduleManager.getModule(Nametags.class).enabled) {
            ci.cancel();
        }
    }
}
