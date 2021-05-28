package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;

public class LoginBypass extends Module {
    public ModeSetting loginModes = new ModeSetting(this, "Mode", "Redesky", "Redesky");

    public LoginBypass() {
        super("AutoLogin", "logs you in on cracked servers", Category.Misc);
    }

    @Override
    public void onEnable() {
        if (loginModes.getMode().equals("Redesky")) {
            mc.player.sendChatMessage("/register redesky redesky");
            /// Bypasses Redesky's dumb antibot
        }
        toggle();
    }
}
