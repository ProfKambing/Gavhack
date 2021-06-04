package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.event.ModeChangeEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.gavhack.setting.NumberSetting;
import me.gavin.gavhack.util.RenderUtil;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;

/**
 * @Created by gerald0mc!!!!
 * gerald0mc not skidder??!?!?!?
 */

public class ESP extends Module {
    public ESP() {
        super("ESP", "Draws a box around stuff.", Category.Render);
        settings.add(renderMode);
        settings.add(players);
        settings.add(items);
        settings.add(animals);
        settings.add(hostiles);
        settings.add(rBox);
        settings.add(gBox);
        settings.add(bBox);
        settings.add(aBox);
        settings.add(rOutline);
        settings.add(gOutline);
        settings.add(bOutline);
        settings.add(aOutline);
    }

    public final ModeSetting renderMode = new ModeSetting(this, "Render Mode", "Both", "Both", "Box", "Outline");
    public final BooleanSetting players = new BooleanSetting(this, "Players", true);
    public final BooleanSetting items = new BooleanSetting(this, "Items", true);
    public final BooleanSetting animals = new BooleanSetting(this, "Animals", false);
    public final BooleanSetting hostiles = new BooleanSetting(this, "Hostiles", true);
    public final NumberSetting rBox = new NumberSetting(this, "Red Box", 255, 1, 255, 0.1f);
    public final NumberSetting gBox = new NumberSetting(this, "Green Box", 255, 1, 255, 0.1f);
    public final NumberSetting bBox = new NumberSetting(this, "Blue Box", 255, 1, 255, 0.1f);
    public final NumberSetting aBox = new NumberSetting(this, "Alpha Box", 255, 1, 255, 0.1f);
    public final NumberSetting rOutline = new NumberSetting(this, "Red Outline", 255, 1, 255, 0.1f);
    public final NumberSetting gOutline = new NumberSetting(this, "Green Outline", 255, 1, 255, 0.1f);
    public final NumberSetting bOutline = new NumberSetting(this, "Blue Outline", 255, 1, 255, 0.1f);
    public final NumberSetting aOutline = new NumberSetting(this, "Alpha Outline", 255, 1, 255, 0.1f);

    private boolean box = false;
    private boolean outline = false;

    @Override
    public void onEnable() {
        this.setMetadata(ChatFormatting.WHITE + "[" + renderMode.getMode() + "]" + ChatFormatting.RESET);
    }

    @Register
    public final Listener<RenderWorldLastEvent> renderListener = event -> {
        for(Entity e : mc.world.getLoadedEntityList()) {
            AxisAlignedBB box2 = e.getEntityBoundingBox();
            double x = (e.posX - e.lastTickPosX) * event.getPartialTicks();
            double y = (e.posY - e.lastTickPosY) * event.getPartialTicks();
            double z = (e.posZ - e.lastTickPosZ) * event.getPartialTicks();

            if(renderMode.getMode().equals("Both")) {
                box = true;
                outline = true;
            }else if(renderMode.getMode().equals("Box")) {
                box = true;
                outline = false;
            }else if(renderMode.getMode().equals("Outline")){
                box = false;
                outline = true;
            }

            if(e == mc.player)
                continue;

            if(e instanceof EntityPlayer && players.value) {
                if(box)
                    RenderUtil.prepare();
                    RenderGlobal.renderFilledBox(x + box2.minX - mc.getRenderManager().viewerPosX, y + box2.minY - mc.getRenderManager().viewerPosY, z + box2.minZ - mc.getRenderManager().viewerPosZ, x + box2.maxX - mc.getRenderManager().viewerPosX, y + box2.maxY - mc.getRenderManager().viewerPosY, z + box2.maxZ - mc.getRenderManager().viewerPosZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                    RenderUtil.release();
                if(outline)
                    RenderUtil.prepare();
                    RenderGlobal.drawBoundingBox(x + box2.minX - mc.getRenderManager().viewerPosX, y + box2.minY - mc.getRenderManager().viewerPosY, z + box2.minZ - mc.getRenderManager().viewerPosZ, x + box2.maxX - mc.getRenderManager().viewerPosX, y + box2.maxY - mc.getRenderManager().viewerPosY, z + box2.maxZ - mc.getRenderManager().viewerPosZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                    RenderUtil.release();
            }

            if(e instanceof EntityItem && items.value) {
                if(box)
                    RenderUtil.prepare();
                    RenderGlobal.renderFilledBox(x + box2.minX - mc.getRenderManager().viewerPosX, y + box2.minY - mc.getRenderManager().viewerPosY, z + box2.minZ - mc.getRenderManager().viewerPosZ, x + box2.maxX - mc.getRenderManager().viewerPosX, y + box2.maxY - mc.getRenderManager().viewerPosY, z + box2.maxZ - mc.getRenderManager().viewerPosZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                    RenderUtil.release();
                if(outline)
                    RenderUtil.prepare();
                    RenderGlobal.drawBoundingBox(x + box2.minX - mc.getRenderManager().viewerPosX, y + box2.minY - mc.getRenderManager().viewerPosY, z + box2.minZ - mc.getRenderManager().viewerPosZ, x + box2.maxX - mc.getRenderManager().viewerPosX, y + box2.maxY - mc.getRenderManager().viewerPosY, z + box2.maxZ - mc.getRenderManager().viewerPosZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                    RenderUtil.release();
            }

            if(e instanceof EntityAnimal && animals.value) {
                if(box)
                    RenderUtil.prepare();
                    RenderGlobal.renderFilledBox(x + box2.minX - mc.getRenderManager().viewerPosX, y + box2.minY - mc.getRenderManager().viewerPosY, z + box2.minZ - mc.getRenderManager().viewerPosZ, x + box2.maxX - mc.getRenderManager().viewerPosX, y + box2.maxY - mc.getRenderManager().viewerPosY, z + box2.maxZ - mc.getRenderManager().viewerPosZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                    RenderUtil.release();
                if(outline)
                    RenderUtil.prepare();
                    RenderGlobal.drawBoundingBox(x + box2.minX - mc.getRenderManager().viewerPosX, y + box2.minY - mc.getRenderManager().viewerPosY, z + box2.minZ - mc.getRenderManager().viewerPosZ, x + box2.maxX - mc.getRenderManager().viewerPosX, y + box2.maxY - mc.getRenderManager().viewerPosY, z + box2.maxZ - mc.getRenderManager().viewerPosZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                    RenderUtil.release();
            }

            if(e instanceof EntityMob && hostiles.value) {
                if(box)
                    RenderUtil.prepare();
                    RenderGlobal.renderFilledBox(x + box2.minX - mc.getRenderManager().viewerPosX, y + box2.minY - mc.getRenderManager().viewerPosY, z + box2.minZ - mc.getRenderManager().viewerPosZ, x + box2.maxX - mc.getRenderManager().viewerPosX, y + box2.maxY - mc.getRenderManager().viewerPosY, z + box2.maxZ - mc.getRenderManager().viewerPosZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                    RenderUtil.release();
                if(outline)
                    RenderUtil.prepare();
                    RenderGlobal.drawBoundingBox(x + box2.minX - mc.getRenderManager().viewerPosX, y + box2.minY - mc.getRenderManager().viewerPosY, z + box2.minZ - mc.getRenderManager().viewerPosZ, x + box2.maxX - mc.getRenderManager().viewerPosX, y + box2.maxY - mc.getRenderManager().viewerPosY, z + box2.maxZ - mc.getRenderManager().viewerPosZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                    RenderUtil.release();
            }
        }
    };

    @Register
    public Listener<ModeChangeEvent> modeChangeListener = event -> {
        if(event.module == this) {
            setMetadata(ChatFormatting.WHITE + "[" + event.newMode + "]" + ChatFormatting.RESET);
        }
    };
}
