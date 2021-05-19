package me.gavin.gavhack.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

public class Utils {

    // clicky :-)
    public static void click() {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getRecord(SoundEvents.UI_BUTTON_CLICK, 1f, 0.25f));
    }
}
