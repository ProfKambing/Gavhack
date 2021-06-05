package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.event.ModeChangeEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.NumberSetting;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.text.TextComponentString;

public class AutoLog extends Module {
    public AutoLog() {
        super("AutoLog", "Will automatically log you when your health reaches the right point.", Category.Combat);
        settings.add(health);
    }

    public final NumberSetting health = new NumberSetting(this, "Health To Log", 5f, 0.5f, 20f, 0.1f);

    @Override
    public void onEnable() {
        this.setMetadata(ChatFormatting.WHITE + "[" + health.value + "]" + ChatFormatting.RESET);
    }

    public void onUpdate() {
        if(mc.player.getHealth() <= health.value) {
            mc.player.connection.sendPacket(new SPacketDisconnect(new TextComponentString("AutoLogged!")));
        }
    }

    @Register
    public Listener<ModeChangeEvent> modeChangeListener = event -> {
        if(event.module == this) {
            setMetadata(ChatFormatting.WHITE + "[" + event.newMode + "]" + ChatFormatting.RESET);
        }
    };
}
