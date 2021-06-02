package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.manager.FriendManager;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.util.MessageUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import org.lwjgl.input.Mouse;

/**
 * @Skidded by gerald0mc
 */

public class MCF extends Module {
    public MCF() {
        super("MCF", "Middle click to friend people.", Category.Misc);
    }

    boolean hasClicked = false;

    public void onUpdate() {
        if(!Mouse.isButtonDown(2)) {
            hasClicked = false;
            return;
        }

        if(!hasClicked) {
            hasClicked = true;

            final RayTraceResult result = mc.objectMouseOver;

            if(result == null || result.typeOfHit != RayTraceResult.Type.ENTITY || !(result.entityHit instanceof EntityPlayer))
                return;

            if(FriendManager.isFriend(mc.objectMouseOver.entityHit.getName())) {
                FriendManager.delFriend(mc.objectMouseOver.entityHit.getName());
                MessageUtil.sendMessagePrefix(ChatFormatting.RED + "Removed " + ChatFormatting.LIGHT_PURPLE + mc.objectMouseOver.entityHit.getName() + ChatFormatting.WHITE + " from friends list");
            }else {
                FriendManager.addFriend(mc.objectMouseOver.entityHit.getName());
                MessageUtil.sendMessagePrefix(ChatFormatting.GREEN + "Added " + ChatFormatting.LIGHT_PURPLE + mc.objectMouseOver.entityHit.getName() + ChatFormatting.WHITE + " to friends list");
            }
        }
    }
}
