package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.friends.Friend;
import me.gavin.gavhack.manager.FriendManager;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.awt.*;

public class Nametags extends Module {

    public Nametags() {
        super("Nametags", "Better nametags", Category.Render);
        settings.add(health);
        settings.add(background);
    }

    public final BooleanSetting health = new BooleanSetting(this, "Health", true);
    public final BooleanSetting background = new BooleanSetting(this, "Background", true);

    @Register
    public final Listener<RenderGameOverlayEvent.Text> render2dListener = event -> {
        for (EntityPlayer player : mc.world.playerEntities) {
            if (player.equals(mc.player))
                continue;

            double yAdd = player.isSneaking() ? 1.75 : 2.25;

            final double lerpX = MathHelper.clampedLerp(player.lastTickPosX, player.posX, event.getPartialTicks());
            final double lerpY = MathHelper.clampedLerp(player.lastTickPosY, player.posY, event.getPartialTicks());
            final double lerpZ = MathHelper.clampedLerp(player.lastTickPosZ, player.posZ, event.getPartialTicks());

            final Vec3d projection = Gavhack.INSTANCE.projectionManager.toScaledScreenPos(new Vec3d(lerpX, lerpY, lerpZ).add(0, yAdd, 0));

            GlStateManager.pushMatrix();
            GlStateManager.translate(projection.x, projection.y, 0);
            String playerName = player.getName();
            float playerHealth = player.getHealth();
            String healthString = String.valueOf(playerHealth);
            Color grey = new Color(255, 255, 255, 255);
            Gui.drawRect(1, 1, 1, 1, -1);
            if(FriendManager.isFriend(playerName)) {
                gavhack.fontRenderer.drawStringWithShadow(playerName, -(mc.fontRenderer.getStringWidth(playerName) / 2d), -(gavhack.fontRenderer.getHeight() + 0.2), new Color(73, 127, 241, 255));
            }else {
                gavhack.fontRenderer.drawStringWithShadow(playerName, -(mc.fontRenderer.getStringWidth(playerName) / 2d), -(gavhack.fontRenderer.getHeight() + 0.2), new Color(-1));
            }
            if(health.value) {
                gavhack.fontRenderer.drawStringWithShadow(healthString, -(mc.fontRenderer.getStringWidth(player.getName()) / 2d) + 27, -(gavhack.fontRenderer.getHeight() + 0.2), new Color(0, 255, 0, 255));
            }
            GlStateManager.popMatrix();
        }
    };
}
