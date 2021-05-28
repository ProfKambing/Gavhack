package me.gavin.gavhack.mixin;

import me.gavin.gavhack.Gavhack;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Redirect(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
    public void createDisplayRedirect(String newTitle) {
        Display.setTitle(">" + Gavhack.MOD_NAME + " " + Gavhack.VERSION);
    }
}
