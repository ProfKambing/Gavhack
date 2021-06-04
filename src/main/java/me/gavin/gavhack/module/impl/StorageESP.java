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
import net.minecraft.tileentity.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class StorageESP extends Module {
    public StorageESP() {
        super("StorageESP", "Draws a box around storage.", Category.Render);
        settings.add(renderMode);
        settings.add(colorMode);
        settings.add(chest);
        settings.add(enderChest);
        settings.add(hopper);
        settings.add(shulker);
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
    public final ModeSetting colorMode = new ModeSetting(this, "Color Mode", "RGBA", "RGBA", "Static");
    public final BooleanSetting chest = new BooleanSetting(this, "Chest", true);
    public final BooleanSetting enderChest = new BooleanSetting(this, "Ender Chest", true);
    public final BooleanSetting hopper = new BooleanSetting(this, "Hopper", true);
    public final BooleanSetting shulker = new BooleanSetting(this, "Shulker", true);
    public final NumberSetting rBox = new NumberSetting(this, "Red Box", 255, 1, 255, 0.1f);
    public final NumberSetting gBox = new NumberSetting(this, "Green Box", 255, 1, 255, 0.1f);
    public final NumberSetting bBox = new NumberSetting(this, "Blue Box", 255, 1, 255, 0.1f);
    public final NumberSetting aBox = new NumberSetting(this, "Alpha Box", 255, 1, 255, 0.1f);
    public final NumberSetting rOutline = new NumberSetting(this, "Red Outline", 255, 1, 255, 0.1f);
    public final NumberSetting gOutline = new NumberSetting(this, "Green Outline", 255, 1, 255, 0.1f);
    public final NumberSetting bOutline = new NumberSetting(this, "Blue Outline", 255, 1, 255, 0.1f);
    public final NumberSetting aOutline = new NumberSetting(this, "Alpha Outline", 255, 1, 255, 0.1f);

    public AxisAlignedBB box2;

    @Override
    public void onEnable() {
        this.setMetadata(ChatFormatting.WHITE + "[" + renderMode.getMode() + "]" + ChatFormatting.RESET);
    }

    @Register
    public final Listener<RenderWorldLastEvent> renderListener = event -> {
        for(TileEntity e : mc.world.loadedTileEntityList) {
            box2 = new AxisAlignedBB(e.getPos()).offset(-mc.getRenderManager().viewerPosX, -mc.getRenderManager().viewerPosY, -mc.getRenderManager().viewerPosZ);
            if(e instanceof TileEntityChest && chest.value) {
                if(renderMode.getMode().equals("Both")) {
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 205 / 255f, 133 / 255f, 63 / 255f, aBox.value / 255f);
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 139 / 255f, 69 / 255f, 19 / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }
                }else if(renderMode.getMode().equals("Box")) {
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 205 / 255f, 133 / 255f, 63 / 255f, aBox.value / 255f);
                        RenderUtil.release();
                    }
                }else if(renderMode.getMode().equals("Outline")){
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 139 / 255f, 69 / 255f, 19 / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }
                }
            }
            if(e instanceof TileEntityEnderChest && enderChest.value) {
                if(renderMode.getMode().equals("Both")) {
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 138 / 255f, 43 / 255f, 226 / 255f, aBox.value / 255f);
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 75 / 255f, 0 / 255f, 130 / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }
                }else if(renderMode.getMode().equals("Box")) {
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 138 / 255f, 43 / 255f, 226 / 255f, aBox.value / 255f);
                        RenderUtil.release();
                    }
                }else if(renderMode.getMode().equals("Outline")){
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 75 / 255f, 0 / 255f, 130 / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }
                }
            }
            if(e instanceof TileEntityHopper && hopper.value) {
                if(renderMode.getMode().equals("Both")) {
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 169 / 255f, 169 / 255f, 169 / 255f, aBox.value / 255f);
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 105 / 255f, 105 / 255f, 105 / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }
                }else if(renderMode.getMode().equals("Box")) {
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 169 / 255f, 169 / 255f, 169 / 255f, aBox.value / 255f);
                        RenderUtil.release();
                    }
                }else if(renderMode.getMode().equals("Outline")){
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 105 / 255f, 105 / 255f, 105 / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }
                }
            }
            if(e instanceof TileEntityShulkerBox && shulker.value) {
                if(renderMode.getMode().equals("Both")) {
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 219 / 255f, 112 / 255f, 147 / 255f, aBox.value / 255f);
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 199 / 255f, 21 / 255f, 133 / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }
                }else if(renderMode.getMode().equals("Box")) {
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, aBox.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.renderFilledBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 138 / 255f, 43 / 255f, 226 / 255f, aBox.value / 255f);
                        RenderUtil.release();
                    }
                }else if(renderMode.getMode().equals("Outline")){
                    if(colorMode.getMode().equals("RGBA")) {
                        RenderUtil.prepare();
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }else {
                        RenderUtil.prepare();
                        RenderGlobal.drawBoundingBox(box2.minX, box2.minY, box2.minZ, box2.maxX, box2.maxY, box2.maxZ, 75 / 255f, 0 / 255f, 130 / 255f, aOutline.value / 255f);
                        RenderUtil.release();
                    }
                }
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
