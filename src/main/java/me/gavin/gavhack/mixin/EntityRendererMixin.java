package me.gavin.gavhack.mixin;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.module.impl.AntiFog;
import me.gavin.gavhack.module.impl.Fullbright;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Inject(method = "setupFog", at = @At("RETURN"), cancellable = true)
    public void setupFogInject(int startCoords, float partialTicks, CallbackInfo ci) {
        if (Gavhack.INSTANCE.moduleManager.getModule(AntiFog.class).enabled) {
            GlStateManager.disableFog();
        }
    }

    @Redirect(method = "updateLightmap(F)V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;gammaSetting:F"))
    public float updateLightmapRedirect(GameSettings settings) {
        boolean flag = Gavhack.INSTANCE.moduleManager.getModule(Fullbright.class).enabled;
        return Math.max(flag ? 100 : -1, settings.gammaSetting);
    }
}
