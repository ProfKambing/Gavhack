package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.gavhack.setting.NumberSetting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class PlayerESP extends Module {
    public PlayerESP() {
        super("PlayerESP", "Draws a box around players, animals, mobs, etc...", Category.Render);
        settings.add(mode);
        settings.add(colorMode);
        settings.add(rBox);
        settings.add(gBox);
        settings.add(bBox);
        settings.add(aBox);
        settings.add(rOutline);
        settings.add(gOutline);
        settings.add(bOutline);
        settings.add(aOutline);
    }

    public final ModeSetting mode = new ModeSetting(this, "Render Mode", "Box", "Box", "Outline", "Both");
    public final ModeSetting colorMode = new ModeSetting(this, "Color Mode", "RGBA", "RGBA", "Rainbow");
    public final NumberSetting rBox = new NumberSetting(this, "Red Box", 255, 1, 255, 1);
    public final NumberSetting gBox = new NumberSetting(this, "Green Box", 255, 1, 255, 1);
    public final NumberSetting bBox = new NumberSetting(this, "Blue Box", 255, 1, 255, 1);
    public final NumberSetting aBox = new NumberSetting(this, "Alpha Box", 255, 1, 255, 1);
    public final NumberSetting rOutline = new NumberSetting(this, "Red Outline", 255, 1, 255, 1);
    public final NumberSetting gOutline = new NumberSetting(this, "Green Outline", 255, 1, 255, 1);
    public final NumberSetting bOutline = new NumberSetting(this, "Blue Outline", 255, 1, 255, 1);
    public final NumberSetting aOutline = new NumberSetting(this, "Alpha Outline", 255, 1, 255, 1);

    public void onRender(RenderWorldLastEvent event) {
        for(Entity e : mc.world.getLoadedEntityList()) {
            if(e instanceof EntityItem) {
                AxisAlignedBB box = e.getEntityBoundingBox();
                double x = (e.posX - e.lastTickPosX) * event.getPartialTicks();
                double y = (e.posY - e.lastTickPosY) * event.getPartialTicks();
                double z = (e.posZ - e.lastTickPosZ) * event.getPartialTicks();

                switch (mode.getMode()) {
                    case "Box":
                        GlStateManager.pushMatrix();
                        GlStateManager.enableBlend();
                        GlStateManager.disableDepth();
                        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                        GlStateManager.disableTexture2D();
                        GlStateManager.depthMask(false);

                        GL11.glEnable(GL11.GL_LINE_SMOOTH);
                        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
                        if (colorMode.getMode().equals("RGBA")) {
                            RenderGlobal.renderFilledBox(x + box.minX, y + box.minY, z + box.minZ, x + box.maxX, y + box.maxY, z + box.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                        } else {
                            colorMode.getMode();
                        }
                        GL11.glDisable(GL11.GL_LINE_SMOOTH);

                        GlStateManager.depthMask(true);
                        GlStateManager.enableDepth();
                        GlStateManager.enableTexture2D();
                        GlStateManager.disableBlend();
                        GlStateManager.popMatrix();
                        break;
                    case "Outline":
                        GlStateManager.pushMatrix();
                        GlStateManager.enableBlend();
                        GlStateManager.disableDepth();
                        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                        GlStateManager.disableTexture2D();
                        GlStateManager.depthMask(false);

                        GL11.glEnable(GL11.GL_LINE_SMOOTH);
                        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
                        if (colorMode.getMode().equals("RGBA")) {
                            RenderGlobal.drawBoundingBox(x + box.minX, y + box.minY, z + box.minZ, x + box.maxX, y + box.maxY, z + box.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                        } else {
                            colorMode.getMode();
                        }
                        GL11.glDisable(GL11.GL_LINE_SMOOTH);

                        GlStateManager.depthMask(true);
                        GlStateManager.enableDepth();
                        GlStateManager.enableTexture2D();
                        GlStateManager.disableBlend();
                        GlStateManager.popMatrix();
                        break;
                    case "Both":
                        GlStateManager.pushMatrix();
                        GlStateManager.enableBlend();
                        GlStateManager.disableDepth();
                        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                        GlStateManager.disableTexture2D();
                        GlStateManager.depthMask(false);

                        GL11.glEnable(GL11.GL_LINE_SMOOTH);
                        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
                        if (colorMode.getMode().equals("RGBA")) {
                            RenderGlobal.drawBoundingBox(x + box.minX, y + box.minY, z + box.minZ, x + box.maxX, y + box.maxY, z + box.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                            RenderGlobal.renderFilledBox(x + box.minX, y + box.minY, z + box.minZ, x + box.maxX, y + box.maxY, z + box.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                        } else if (colorMode.getMode().equals("Rainbow")) {

                        }
                        GL11.glDisable(GL11.GL_LINE_SMOOTH);

                        GlStateManager.depthMask(true);
                        GlStateManager.enableDepth();
                        GlStateManager.enableTexture2D();
                        GlStateManager.disableBlend();
                        GlStateManager.popMatrix();
                        break;
                }
            }
        }
    }
}
