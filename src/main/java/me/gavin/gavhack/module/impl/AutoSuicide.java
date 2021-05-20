package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;

public class AutoSuicide extends Module {
    public AutoSuicide() {
        super("AutoSuicide", "Automatically kills you.", Category.Player);
        settings.add(announceUsage);
    }

    public final BooleanSetting announceUsage = new BooleanSetting("Announce Usage", true);

    public void onEnable() {
        if(announceUsage.value) {
            mc.player.sendChatMessage("I just used GavHack AutoSuicide to commit suicide ez");
        }
        mc.player.sendChatMessage("/kill");
        toggle();
    }
}
