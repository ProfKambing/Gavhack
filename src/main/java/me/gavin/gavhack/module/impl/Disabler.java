package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.event.ModeChangeEvent;
import me.gavin.gavhack.event.PacketEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerAbilities;

public class Disabler extends Module {
    private boolean enableTransaction = false;

    public ModeSetting disablerModes = new ModeSetting(this, "Mode", "Transaction", "Transaction", "Funny");

    public Disabler() {
        super("Disabler", "Disables some anticheats.", Category.Misc);
        this.settings.add(disablerModes);
    }

    @Register
    public final Listener<PacketEvent.Outgoing> packetListener = event -> {
        if (event.getPacket() instanceof CPacketConfirmTransaction && enableTransaction) {
            event.cancelled = true;
        }
    };

    @Override
    public void onEnable() {
        this.setMetadata(ChatFormatting.WHITE + "[" + disablerModes.getMode() + "]" + ChatFormatting.RESET);
        if (disablerModes.getMode().equals("Transaction")) {
            enableTransaction = true;
        }
        else if (disablerModes.getMode().equals("Funny")) {
            mc.player.connection.sendPacket(new CPacketPlayerAbilities());
            mc.player.connection.sendPacket(new CPacketPlayer.Position());
            toggle();
        }
    }

    @Register
    public Listener<ModeChangeEvent> modeChangeListener = event -> {
        if(event.module == this) {
            setMetadata(ChatFormatting.WHITE + "[" + event.newMode + "]" + ChatFormatting.RESET);
        }
    };
}
