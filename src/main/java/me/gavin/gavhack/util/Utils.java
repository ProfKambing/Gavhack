package me.gavin.gavhack.util;

import me.gavin.gavhack.mixin.accessor.IRenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

public class Utils {
    // clicky :-)
    public static void click() {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getRecord(SoundEvents.UI_BUTTON_CLICK, 1f, 0.25f));
    }

    public static double[] getRenderPos() {
        IRenderManager manager = (IRenderManager) Minecraft.getMinecraft().getRenderManager();
        return new double[] {manager.getRenderPosX(), manager.getRenderPosY(), manager.getRenderPosZ()};
    }
}
