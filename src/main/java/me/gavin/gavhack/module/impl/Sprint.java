package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Sprint extends Module {

    private final ModeSetting sprintMode = new ModeSetting("Mode", "Legit", "Legit", "Rage");

    public Sprint() {
        super("Sprint", "Sprint automatically", Category.Movement);
        this.settings.add(sprintMode);
    }

    @Register
    public Listener<TickEvent.PlayerTickEvent> tickListener = event -> {
        if (sprintMode.getMode().equals("Legit")) {
            if (mc.player.moveForward > 0) {
                if (!mc.player.isSneaking() && !mc.player.isHandActive() && !mc.player.collidedHorizontally) {
                    mc.player.setSprinting(true);
                }
            }
        } else {
            if (mc.player.moveForward != 0 || mc.player.moveStrafing != 0) {
                mc.player.setSprinting(true);
            }
        }
    };
}
