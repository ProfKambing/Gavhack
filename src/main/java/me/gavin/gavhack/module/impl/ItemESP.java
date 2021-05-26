package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.gavhack.setting.NumberSetting;
import me.gavin.gavhack.util.RenderUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class ItemESP extends Module {
    public ItemESP() {
        super("ItemESP", "Draws a box around items.", Category.Render);
        settings.add(mode);
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
    public final NumberSetting rBox = new NumberSetting(this, "Red Box", 255, 1, 255, 1);
    public final NumberSetting gBox = new NumberSetting(this, "Green Box", 255, 1, 255, 1);
    public final NumberSetting bBox = new NumberSetting(this, "Blue Box", 255, 1, 255, 1);
    public final NumberSetting aBox = new NumberSetting(this, "Alpha Box", 255, 1, 255, 1);
    public final NumberSetting rOutline = new NumberSetting(this, "Red Outline", 255, 1, 255, 1);
    public final NumberSetting gOutline = new NumberSetting(this, "Green Outline", 255, 1, 255, 1);
    public final NumberSetting bOutline = new NumberSetting(this, "Blue Outline", 255, 1, 255, 1);
    public final NumberSetting aOutline = new NumberSetting(this, "Alpha Outline", 255, 1, 255, 1);

    public Boolean box = false;
    public Boolean outline = false;

    public void onRender(RenderWorldLastEvent event) {
        for(Entity e : mc.world.getLoadedEntityList()) {
            if(e instanceof EntityItem) {
                AxisAlignedBB box2 = e.getEntityBoundingBox();
                double x = (e.posX - e.lastTickPosX) * event.getPartialTicks();
                double y = (e.posY - e.lastTickPosY) * event.getPartialTicks();
                double z = (e.posZ - e.lastTickPosZ) * event.getPartialTicks();

                if(mode.getMode().equals("Box")) {
                    box = true;
                    outline = false;
                }

                if(mode.getMode().equals("Outline")) {
                    box = false;
                    outline = true;
                }

                if(mode.getMode().equals("Both")) {
                    box = true;
                    outline = true;
                }

                if(box) {
                    RenderUtil.prepare();
                    RenderGlobal.renderFilledBox(x + box2.minX, y + box2.minY, z + box2.minZ, x + box2.maxX, y + box2.maxY, z + box2.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                    RenderUtil.release();
                }
                if(outline) {
                    RenderUtil.prepare();
                    RenderGlobal.drawBoundingBox(x + box2.minX, y + box2.minY, z + box2.minZ, x + box2.maxX, y + box2.maxY, z + box2.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                    RenderUtil.release();
                }
            }
        }
    }
}
