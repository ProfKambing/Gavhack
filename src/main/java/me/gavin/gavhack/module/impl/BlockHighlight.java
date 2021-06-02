package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.event.ModeChangeEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.gavhack.setting.NumberSetting;
import me.gavin.gavhack.util.RenderUtil;
import me.gavin.gavhack.util.Utils;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class BlockHighlight extends Module {
    public BlockHighlight() {
        super("BlockHighlight", "Places a block around whatever you are looking at.", Category.Render);
        settings.add(mode);
        settings.add(lineWidth);
        settings.add(rBox);
        settings.add(gBox);
        settings.add(bBox);
        settings.add(rOutline);
        settings.add(gOutline);
        settings.add(bOutline);
    }

    public final ModeSetting mode = new ModeSetting(this, "Render Mode", "Outline", "Both", "Box", "Outline");
    public final NumberSetting lineWidth = new NumberSetting(this, "Line Width", 1, 0, 5, 0.1f);
    public final NumberSetting rBox = new NumberSetting(this, "Red Box", 255, 1, 255, 1);
    public final NumberSetting gBox = new NumberSetting(this, "Green Box", 255, 1, 255, 1);
    public final NumberSetting bBox = new NumberSetting(this, "Blue Box", 255, 1, 255, 1);
    public final NumberSetting rOutline = new NumberSetting(this, "Red Outline", 255, 1, 255, 1);
    public final NumberSetting gOutline = new NumberSetting(this, "Green Outline", 255, 1, 255, 1);
    public final NumberSetting bOutline = new NumberSetting(this, "Blue Outline", 255, 1, 255, 1);

    private boolean box = false;
    private boolean outline = false;

    @Override
    public void onEnable() {
        this.setMetadata(ChatFormatting.WHITE + "[" + mode.getMode() + "]" + ChatFormatting.RESET);
    }

    @Register
    public final Listener<RenderWorldLastEvent> eventListener = event -> {
        RayTraceResult rayTraceResult = mc.objectMouseOver;
        if(rayTraceResult != null) {
            if (rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
                if(mode.getMode().equals("Both")) {
                    box = true;
                    outline = true;
                }else if(mode.getMode().equals("Box")) {
                    box = true;
                    outline = false;
                }else if(mode.getMode().equals("Outline")){
                    box = false;
                    outline = true;
                }

                AxisAlignedBB box2 = mc.world.getBlockState(rayTraceResult.getBlockPos()).getSelectedBoundingBox(mc.world, rayTraceResult.getBlockPos());

                if(box) {
                    RenderUtil.prepare();
                    double[] renderPos = Utils.getRenderPos();
                    GL11.glLineWidth(lineWidth.value);
                    RenderGlobal.renderFilledBox(box2.offset(-renderPos[0], -renderPos[1], -renderPos[2]), rBox.value / 255f, gBox.value / 255f, bBox.value / 255f, 0.5f);
                    RenderUtil.release();
                }
                if(outline) {
                    RenderUtil.prepare();
                    double[] renderPos = Utils.getRenderPos();
                    GL11.glLineWidth(lineWidth.value);
                    RenderGlobal.drawSelectionBoundingBox(box2.offset(-renderPos[0], -renderPos[1], -renderPos[2]), rOutline.value / 255f, gOutline.value / 255f, bOutline.value / 255f, 0.5f);
                    RenderUtil.release();
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
