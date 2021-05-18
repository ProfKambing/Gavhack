package me.gavin.gavhack.mixin;

import me.gavin.gavhack.Gavhack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin {

    @Inject(method = "drawScreen", at = @At("TAIL"))
    public void drawScreenInject(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(Gavhack.MOD_NAME + " " + Gavhack.VERSION, 2, 2, -1);
    }
}
