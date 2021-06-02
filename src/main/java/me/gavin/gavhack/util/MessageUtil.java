package me.gavin.gavhack.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class MessageUtil {

    public static String prefix = TextFormatting.WHITE + "[" + TextFormatting.BLUE + "GavHack" + TextFormatting.WHITE + "]" + TextFormatting.WHITE;

    private static final EntityPlayerSP player = Minecraft.getMinecraft().player;

    public static void sendRawMessage(String message) {
        player.sendMessage(new TextComponentString(message));
    }

    public static void sendMessagePrefix(String message) {
        sendRawMessage(prefix + " " + message);
    }

    public static void sendMessageInt(String msg, int posX, int posY, int posZ) {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString(msg.replace(" ", TextFormatting.WHITE + " ")));
    }
}
