package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.event.ModeChangeEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;

public class LoginBypass extends Module {
    public ModeSetting loginModes = new ModeSetting(this, "Mode", "Redesky", "Redesky");

    public LoginBypass() {
        super("AutoLogin", "logs you in on cracked servers", Category.Misc);
    }

    @Override
    public void onEnable() {
        this.setMetadata(ChatFormatting.WHITE + "[" + loginModes.getMode() + "]" + ChatFormatting.RESET);
        if (loginModes.getMode().equals("Redesky")) {
            mc.player.sendChatMessage("/register redesky redesky");
            /// Bypasses Redesky's dumb antibot
        }
        toggle();
    }

    @Register
    public Listener<ModeChangeEvent> modeChangeListener = event -> {
        if(event.module == this) {
            setMetadata(ChatFormatting.WHITE + "[" + event.newMode + "]" + ChatFormatting.RESET);
        }
    };
}
