package me.gavin.gavhack.manager;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;

public class DiscordManager {

    private final String appId = "842208832037978112";

    private final DiscordRichPresence richPresence;
    private final DiscordRPC rpc;
    private final DiscordEventHandlers eventHandlers;

    public DiscordManager() {
        richPresence = new DiscordRichPresence();
        rpc = DiscordRPC.INSTANCE;
        eventHandlers = new DiscordEventHandlers();
    }

    public void startRPC() {
        rpc.Discord_Initialize(appId, eventHandlers, true, null);
        richPresence.largeImageKey = "gav_pig";
        richPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        richPresence.details = "Playing as " + Minecraft.getMinecraft().getSession().getUsername();
        rpc.Discord_UpdatePresence(richPresence);
    }

    public void stopRPC() {
        rpc.Discord_ClearPresence();
        rpc.Discord_Shutdown();
    }
}
