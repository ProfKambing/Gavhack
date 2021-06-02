package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.util.MessageUtil;

public class BedLocate extends Module {
    public BedLocate() {
        super("BedLocate", "Locates your bed on a server.", Category.Chat);
    }

    @Override
    public void onEnable() {
        String bedLocation = mc.player.getBedLocation().toString();
        MessageUtil.sendMessagePrefix("Your bed is at " + bedLocation);
        toggle();
    }
}
