package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.mixin.accessor.IMinecraft;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;
import me.gavin.gavhack.setting.NumberSetting;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FastUse extends Module {

    private final BooleanSetting crystals = new BooleanSetting(this, "Crystals", true);
    private final BooleanSetting xp = new BooleanSetting(this, "XP", true);

    public FastUse() {
        super("FastUse", "Use crystals and xp faster", Category.Player);
        this.settings.add(crystals);
        this.settings.add(xp);
    }

    @Register
    public final Listener<TickEvent.PlayerTickEvent> playerTickListener = event -> {
        if (isHolding(Items.EXPERIENCE_BOTTLE) || isHolding(Items.END_CRYSTAL)) {
            ((IMinecraft)mc).setRightClickDelay(0);
        }
    };

    private boolean isHolding(Item item) {
        return mc.player.getHeldItemMainhand().getItem() == item || mc.player.getHeldItemOffhand().getItem() == item;
    }
}
