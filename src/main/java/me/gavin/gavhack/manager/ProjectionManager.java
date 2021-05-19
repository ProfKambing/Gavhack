package me.gavin.gavhack.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

// adapted from KAMI blue's ProjectionUtils.kt
// manually converted from kotlin to java
public class ProjectionManager {

    private final Minecraft mc = Minecraft.getMinecraft();
    private final Matrix4f modelMatrix = new Matrix4f();
    private final Matrix4f projectionMatrix = new Matrix4f();
    static Vec3d camPos = new Vec3d(0.0, 0.0, 0.0);

    public void updateMatrix() {
        if (mc.getRenderViewEntity() == null) return;
        final Vec3d viewerPos = ActiveRenderInfo.projectViewFromEntity(mc.getRenderViewEntity(), mc.getRenderPartialTicks());
        final Vec3d relativeCamPos = ActiveRenderInfo.getCameraPosition();

        loadMatrix(modelMatrix, GL_MODELVIEW_MATRIX);
        loadMatrix(projectionMatrix, GL_PROJECTION_MATRIX);
        camPos = viewerPos.add(relativeCamPos);
    }

    private static void loadMatrix(Matrix4f matrix, int glBit) {
        final FloatBuffer floatBuffer = GLAllocation.createDirectFloatBuffer(16);
        glGetFloat(glBit, floatBuffer);
        matrix.load(floatBuffer);
    }

    public Vec3d toScaledScreenPos(Vec3d posIn) {
        final Vector4f vector4f = getTransformedMatrix(posIn);

        final ScaledResolution scaledResolution = new ScaledResolution(mc);
        final int width = scaledResolution.getScaledWidth();
        final int height = scaledResolution.getScaledHeight();

        vector4f.x = width / 2f + (0.5f * vector4f.x * width + 0.5f);
        vector4f.y = height / 2f - (0.5f * vector4f.y * height + 0.5f);
        final double posZ = isVisible(vector4f, width, height) ? 0.0 : -1.0;

        return new Vec3d(vector4f.x, vector4f.y, posZ);
    }

    public Vec3d toScreenPos(Vec3d posIn) {
        final Vector4f vector4f = getTransformedMatrix(posIn);

        final int width = mc.displayWidth;
        final int height = mc.displayHeight;

        vector4f.x = width / 2f + (0.5f * vector4f.x * width + 0.5f);
        vector4f.y = height / 2f - (0.5f * vector4f.y * height + 0.5f);
        final double posZ = isVisible(vector4f, width, height) ? 0.0 : -1.0;

        return new Vec3d(vector4f.x, vector4f.y, posZ);
    }

    private Vector4f getTransformedMatrix(Vec3d posIn) {
        final Vec3d relativePos = camPos.subtract(posIn);
        final Vector4f vector4f = new Vector4f((float)relativePos.x, (float)relativePos.y, (float)relativePos.z, 1f);

        transform(vector4f, modelMatrix);
        transform(vector4f, projectionMatrix);

        if (vector4f.w > 0.0f) {
            vector4f.x *= -100000;
            vector4f.y *= -100000;
        } else {
            final float invert = 1f / vector4f.w;
            vector4f.x *= invert;
            vector4f.y *= invert;
        }

        return vector4f;
    }

    private void transform(Vector4f vec, Matrix4f matrix) {
        final float x = vec.x;
        final float y = vec.y;
        final float z = vec.z;
        vec.x = x * matrix.m00 + y * matrix.m10 + z * matrix.m20 + matrix.m30;
        vec.y = x * matrix.m01 + y * matrix.m11 + z * matrix.m21 + matrix.m31;
        vec.z = x * matrix.m02 + y * matrix.m12 + z * matrix.m22 + matrix.m32;
        vec.w = x * matrix.m03 + y * matrix.m13 + z * matrix.m23 + matrix.m33;
    }

    // stolen from a decompiler kek
    private boolean isVisible(Vector4f pos, int width, int height) {
        double var6 = width;
        double var4 = pos.x;
        if (var4 >= 0.0D && var4 <= var6) {
            var6 = height;
            var4 = pos.y;
            return var4 >= 0.0D && var4 <= var6;
        }

        return false;
    }

    private ICamera camera = new Frustum();

    // seppuku :troll:
    public float[] getCornerPositionsForEntity(Entity e, float partialTicks, int width, int height) {
        float x = -1;
        float y = -1;
        float w = width + 1;
        float h = height + 1;


        final Vec3d pos = new Vec3d(
                MathHelper.clampedLerp(e.lastTickPosX, e.posX, partialTicks),
                MathHelper.clampedLerp(e.lastTickPosY, e.posY, partialTicks),
                MathHelper.clampedLerp(e.lastTickPosZ, e.posZ, partialTicks));

        AxisAlignedBB bb = e.getEntityBoundingBox();

        if (e instanceof EntityEnderCrystal) {
            bb = new AxisAlignedBB(bb.minX + 0.3f, bb.minY + 0.2f, bb.minZ + 0.3f, bb.maxX - 0.3f, bb.maxY, bb.maxZ - 0.3f);
        }

        if (e instanceof EntityItem) {
            bb = new AxisAlignedBB(bb.minX, bb.minY + 0.7f, bb.minZ, bb.maxX, bb.maxY, bb.maxZ);
        }

        bb = bb.expand(0.15f, 0.1f, 0.15f);

        camera.setPosition(mc.getRenderViewEntity().posX, mc.getRenderViewEntity().posY, mc.getRenderViewEntity().posZ);

        if (!camera.isBoundingBoxInFrustum(bb)) {
            return null;
        }

        final Vec3d[] corners = {
                new Vec3d(bb.minX - bb.maxX + e.width / 2, 0, bb.minZ - bb.maxZ + e.width / 2),
                new Vec3d(bb.maxX - bb.minX - e.width / 2, 0, bb.minZ - bb.maxZ + e.width / 2),
                new Vec3d(bb.minX - bb.maxX + e.width / 2, 0, bb.maxZ - bb.minZ - e.width / 2),
                new Vec3d(bb.maxX - bb.minX - e.width / 2, 0, bb.maxZ - bb.minZ - e.width / 2),

                new Vec3d(bb.minX - bb.maxX + e.width / 2, bb.maxY - bb.minY, bb.minZ - bb.maxZ + e.width / 2),
                new Vec3d(bb.maxX - bb.minX - e.width / 2, bb.maxY - bb.minY, bb.minZ - bb.maxZ + e.width / 2),
                new Vec3d(bb.minX - bb.maxX + e.width / 2, bb.maxY - bb.minY, bb.maxZ - bb.minZ - e.width / 2),
                new Vec3d(bb.maxX - bb.minX - e.width / 2, bb.maxY - bb.minY, bb.maxZ - bb.minZ - e.width / 2)
        };

        for (Vec3d vec : corners) {
            final Vec3d projection = toScaledScreenPos(new Vec3d(
                    pos.x + vec.x,
                    pos.y + vec.y,
                    pos.z + vec.z));

            x = Math.max(x, (float) projection.x);
            y = Math.max(y, (float) projection.y);

            w = Math.min(w, (float) projection.x);
            h = Math.min(h, (float) projection.y);
        }

        if (x != -1 && y != -1 && w != width + 1 && h != height + 1) {
            return new float[]{x, y, w, h};
        }

        return null;
    }
}