package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.event.ModeChangeEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
    public ChatSuffix() {
        super("ChatSuffix", "Adds swag to the end of your chat.", Category.Chat);
        settings.add(mode);
    }

    public final ModeSetting mode = new ModeSetting(this, "Suffix Mode", "Normal", "Normal", "Tranny", "Advertisement");

    private final String normalSuffix = " | GavHack";
    private final String transSuffix = " | GavHack Trans Edition <3";
    private final String adSuffix = " | GavHack | https://discord.gg/usCEy8yF7m";

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
        if (event.getMessage().startsWith("!")) return;
        if (event.getMessage().startsWith("/")) return;
        if (event.getMessage().startsWith(Gavhack.PREFIX)) return;
        if (event.getMessage().contains(normalSuffix)) return;
        if (event.getMessage().contains(transSuffix)) return;
        if (mode.getMode().equals("Tranny")) {
            event.setMessage(event.getMessage() + transSuffix);
        }else if(mode.getMode().equals("Advertisement")) {
            event.setMessage(event.getMessage() + adSuffix);
        }else {
            event.setMessage(event.getMessage() + normalSuffix);
        }
    }
}
