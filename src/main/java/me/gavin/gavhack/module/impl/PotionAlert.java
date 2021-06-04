package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;
import me.gavin.gavhack.util.MessageUtil;
import net.minecraft.init.MobEffects;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PotionAlert extends Module {
    public PotionAlert() {
        super("PotionAlert", "Alerts you when you are hit with a potion effect.", Category.Player);
        settings.add(weakness);
        settings.add(slowness);
        settings.add(strength);
        settings.add(swiftness);
    }

    public final BooleanSetting weakness = new BooleanSetting(this, "Weakness", true);
    public final BooleanSetting slowness = new BooleanSetting(this, "Slowness", true);
    public final BooleanSetting strength = new BooleanSetting(this, "Strength", true);
    public final BooleanSetting swiftness = new BooleanSetting(this, "Swiftness", true);

    public boolean hasAnnounced = false;

    public void onTick(TickEvent event) {
        if(weakness.value) {
            if(mc.player.isPotionActive(MobEffects.WEAKNESS) && !hasAnnounced) {
                hasAnnounced = true;
                MessageUtil.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "PotionAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Hey" + ChatFormatting.GRAY + ", " + ChatFormatting.AQUA + mc.getSession().getUsername() + ChatFormatting.GRAY + "," + ChatFormatting.WHITE + " unlucky move retard" + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + "you now have " + ChatFormatting.RED + "weakness");
            }
            if(!mc.player.isPotionActive(MobEffects.WEAKNESS) && hasAnnounced) {
                hasAnnounced = false;
                MessageUtil.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "PotionAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Phew" + ChatFormatting.GRAY + ", " + ChatFormatting.AQUA + mc.getSession().getUsername() + ChatFormatting.GRAY + "," + ChatFormatting.WHITE + " that was close" + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + "you no longer have " + ChatFormatting.RED + "weakness");
            }
        }
        if(slowness.value) {
            if(mc.player.isPotionActive(MobEffects.SLOWNESS) && !hasAnnounced) {
                hasAnnounced = true;
                MessageUtil.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "PotionAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Hey" + ChatFormatting.GRAY + ", " + ChatFormatting.AQUA + mc.getSession().getUsername() + ChatFormatting.GRAY + "," + ChatFormatting.WHITE + " unlucky move mate" + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + "you now have " + ChatFormatting.RED + "slowness");
            }
            if(!mc.player.isPotionActive(MobEffects.SLOWNESS) && hasAnnounced) {
                hasAnnounced = false;
                MessageUtil.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "PotionAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Phew" + ChatFormatting.GRAY + ", " + ChatFormatting.AQUA + mc.getSession().getUsername() + ChatFormatting.GRAY + "," + ChatFormatting.WHITE + " that was close" + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + "you no longer have " + ChatFormatting.RED + "slowness");
            }
        }
        if(strength.value) {
            if(mc.player.isPotionActive(MobEffects.STRENGTH) && !hasAnnounced) {
                hasAnnounced = true;
                MessageUtil.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "PotionAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Hey" + ChatFormatting.GRAY + ", " + ChatFormatting.AQUA + mc.getSession().getUsername() + ChatFormatting.GRAY + "," + ChatFormatting.WHITE + " lucky move mate" + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + "you now have " + ChatFormatting.RED + "strength");
            }
            if(!mc.player.isPotionActive(MobEffects.STRENGTH) && hasAnnounced) {
                hasAnnounced = false;
                MessageUtil.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "PotionAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Hey" + ChatFormatting.GRAY + ", " + ChatFormatting.AQUA + mc.getSession().getUsername() + ChatFormatting.GRAY + "," + ChatFormatting.WHITE + " rip" + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + "you no longer have " + ChatFormatting.RED + "strength");
            }
        }
        if(swiftness.value) {
            if(mc.player.isPotionActive(MobEffects.SPEED) && !hasAnnounced) {
                hasAnnounced = true;
                MessageUtil.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "PotionAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Hey" + ChatFormatting.GRAY + ", " + ChatFormatting.AQUA + mc.getSession().getUsername() + ChatFormatting.GRAY + "," + ChatFormatting.WHITE + " lucky move mate" + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + "you now have " + ChatFormatting.RED + "speed");
            }
            if(!mc.player.isPotionActive(MobEffects.SPEED) && hasAnnounced) {
                hasAnnounced = false;
                MessageUtil.sendMessagePrefix(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "PotionAlert" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "Hey" + ChatFormatting.GRAY + ", " + ChatFormatting.AQUA + mc.getSession().getUsername() + ChatFormatting.GRAY + "," + ChatFormatting.WHITE + " rip" + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + "you no longer have " + ChatFormatting.RED + "speed");
            }
        }
    }
}
