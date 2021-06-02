package me.gavin.gavhack.mixin;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.module.impl.GuiMove;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInputFromOptions;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MovementInputFromOptions.class)
public class MovementInputFromOptionsMixin {

    @Redirect(method = "updatePlayerMoveState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
    public boolean isKeyPressed(KeyBinding keyBinding) {

        int keycode = keyBinding.getKeyCode();

        if (keycode != 0) {
            if (Gavhack.INSTANCE.moduleManager.getModule(GuiMove.class).enabled) {
                if (Minecraft.getMinecraft().currentScreen != null && !(Minecraft.getMinecraft().currentScreen instanceof GuiChat)) {
                    if (keycode != Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode()) {
                        return Keyboard.isKeyDown(keycode);
                    }
                }
            }
        }

        return keyBinding.isKeyDown();
    }
}
