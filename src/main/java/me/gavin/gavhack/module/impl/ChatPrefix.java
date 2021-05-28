package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatPrefix extends Module {
    public ModeSetting mode = new ModeSetting(this, "Mode", "Green", "Green", "Gold");

    public ChatPrefix() {
        super("ChatPrefix", "Changes the color of your chat message on some servers.", Category.Chat);
    }

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String suffix = "  \u1d04\u1d1c\u1d0d\u029c\u1d00\u1d04\u1d0b";
        if (event.getMessage().startsWith("/")) return;
        if (event.getMessage().startsWith("*")) return;
        if (event.getMessage().startsWith(".")) return;
        if (event.getMessage().startsWith(",")) return;
        if(event.getMessage().startsWith("!")) return;
        if (event.getMessage().startsWith(Gavhack.PREFIX)) return;
        if (mode.getMode().equals("Green")) {
            event.setMessage("> " + event.getMessage());
        }
        else if (mode.getMode().equals("Gold")) {
            event.setMessage("$ " + event.getMessage());
        }
    }
}
