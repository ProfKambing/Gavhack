package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
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
        settings.add(lineWidth);
        settings.add(red);
        settings.add(green);
        settings.add(blue);
    }

    public final NumberSetting lineWidth = new NumberSetting(this, "Line Width", 1, 0, 5, 0.1f);
    public final NumberSetting red = new NumberSetting(this, "Red", 255, 0, 255, 1);
    public final NumberSetting green = new NumberSetting(this, "Green", 255, 0, 255, 1);
    public final NumberSetting blue = new NumberSetting(this, "Blue", 255, 0, 255, 1);

    @Register
    public final Listener<RenderWorldLastEvent> eventListener = event -> {
        RayTraceResult rayTraceResult = mc.objectMouseOver;
        if(rayTraceResult != null) {
            if (rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
                AxisAlignedBB box = mc.world.getBlockState(rayTraceResult.getBlockPos()).getSelectedBoundingBox(mc.world, rayTraceResult.getBlockPos());

                RenderUtil.prepare();
                double[] renderPos = Utils.getRenderPos();
                GL11.glLineWidth(lineWidth.value);
                RenderGlobal.drawSelectionBoundingBox(box.offset(-renderPos[0], -renderPos[1], -renderPos[2]), red.value / 255f, green.value / 255f, blue.value / 255f, 0.5f);
                RenderUtil.release();
            }
        }
    };
}
