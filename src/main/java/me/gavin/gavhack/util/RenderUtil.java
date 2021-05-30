package me.gavin.gavhack.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL32;

import static org.lwjgl.opengl.GL11.*;

import java.awt.*;

public class RenderUtil {

    public static void renderItemStack(ItemStack stack, int x, int y) {
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
        GlStateManager.clear(GL_DEPTH_BUFFER_BIT);
        GlStateManager.enableDepth();
        GlStateManager.disableAlpha();
        Minecraft.getMinecraft().getRenderItem().zLevel = -150.0f;
        RenderHelper.enableStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
        Minecraft.getMinecraft().getRenderItem().renderItemOverlays(Minecraft.getMinecraft().fontRenderer, stack, x, y);
        RenderHelper.disableStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().zLevel = 0.0f;
    }

    public static void prepare() {
        glPushMatrix();
        glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        GlStateManager.tryBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ZERO, GL_ONE);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        glEnable(GL_LINE_SMOOTH);
        glEnable(GL32.GL_DEPTH_CLAMP);
    }

    public static void release() {
        glDisable(GL32.GL_DEPTH_CLAMP);
        glDisable(GL_LINE_SMOOTH);
        GlStateManager.enableAlpha();
        GlStateManager.enableCull();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.glLineWidth(1.0f);
        glHint(GL_LINE_SMOOTH_HINT, GL_DONT_CARE);
        glPopMatrix();
    }

    public static void line3d(Vec3d pos1, Vec3d pos2, float lineWidth, Color color) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        glLineWidth(lineWidth);
        bufferBuilder.begin(GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(pos1.x, pos1.y, pos1.z).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(pos2.x, pos2.y, pos2.z).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.draw();
    }

    /**
     * This is from Tracers.java just thought
     * might as well since its pretty good :/
     */
    public static void drawLine(Entity e, Color color, float partialTicks) {
        double lerpX = MathHelper.clampedLerp(e.lastTickPosX, e.posX, partialTicks);
        double lerpY = MathHelper.clampedLerp(e.lastTickPosY, e.posY, partialTicks);
        double lerpZ = MathHelper.clampedLerp(e.lastTickPosZ, e.posZ, partialTicks);
        final Vec3d vec = new Vec3d(lerpX, lerpY, lerpZ).subtract(
                Minecraft.getMinecraft().getRenderManager().viewerPosX,
                Minecraft.getMinecraft().getRenderManager().viewerPosY,
                Minecraft.getMinecraft().getRenderManager().viewerPosZ);

        RenderUtil.prepare();
        GL11.glShadeModel(GL11.GL_SMOOTH);
        RenderUtil.line3d(ActiveRenderInfo.getCameraPosition(), vec, 1f, color);
        RenderUtil.release();
    }
}
