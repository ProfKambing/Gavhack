package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
    public ChatSuffix() {
        super("ChatSuffix", "Adds swag to the end of your chat.", Category.Chat);
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
        if (event.getMessage().contains(suffix)) return;
        event.setMessage(event.getMessage() + suffix);
    }
}
