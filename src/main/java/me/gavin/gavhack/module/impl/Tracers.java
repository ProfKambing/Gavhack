package me.gavin.gavhack.module.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.event.ModeChangeEvent;
import me.gavin.gavhack.friends.Friend;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.BooleanSetting;
import me.gavin.gavhack.setting.ModeSetting;
import me.gavin.gavhack.util.RenderUtil;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Tracers extends Module {

    private final BooleanSetting posts = new BooleanSetting(this, "Posts", true);

    private final BooleanSetting players = new BooleanSetting(this, "Players", true);
    private final BooleanSetting friends = new BooleanSetting(this, "Friends", true);
    private final BooleanSetting mobs = new BooleanSetting(this, "Mobs", false);
    private final BooleanSetting animals = new BooleanSetting(this, "Animals", false);
    private final BooleanSetting items = new BooleanSetting(this, "Items", true);

    private final ModeSetting colors = new ModeSetting(this, "Colors", "Static", "Static", "Custom");

    public Tracers() {
        super("Tracers", "Draw lines to other entities", Category.Render);
        this.settings.add(posts);
        this.settings.add(players);
        this.settings.add(mobs);
        this.settings.add(animals);
        this.settings.add(items);
        this.settings.add(colors);
    }

    private final Color itemColor = Color.ORANGE;
    private final Color playerColor = Color.RED;
    private final Color friendColor = Color.CYAN;
    private final Color monsterColor = Color.RED;
    private final Color animalColor = Color.GREEN;

    @Override
    public void onEnable() {
        this.setMetadata(ChatFormatting.WHITE + "[" + colors.getMode() + "]" + ChatFormatting.RESET);
    }

    @Register
    public final Listener<RenderWorldLastEvent> renderListener = event -> {
        for (Entity e : mc.world.loadedEntityList) {
            if (e.equals(mc.player))
                continue;

            final Color c = gavhack.colorManager.asColor();
            final boolean custom = colors.getMode().equals("Custom");

            if (e instanceof EntityPlayer && players.value) {
                drawLine(e, custom ? c : playerColor, event.getPartialTicks());
                continue;
            }

            if (e instanceof EntityMob && mobs.value) {
                drawLine(e, custom ? c : monsterColor, event.getPartialTicks());
                continue;
            }

            if (e instanceof EntityAnimal && animals.value) {
                drawLine(e, custom ? c : animalColor, event.getPartialTicks());
                continue;
            }

            if (e instanceof EntityItem && items.value) {
                drawLine(e, custom ? c : itemColor, event.getPartialTicks());
            }
        }
    };

    private void drawLine(Entity e, Color color, float partialTicks) {

        double lerpX = MathHelper.clampedLerp(e.lastTickPosX, e.posX, partialTicks);
        double lerpY = MathHelper.clampedLerp(e.lastTickPosY, e.posY, partialTicks);
        double lerpZ = MathHelper.clampedLerp(e.lastTickPosZ, e.posZ, partialTicks);
        final Vec3d vec = new Vec3d(lerpX, lerpY, lerpZ).subtract(
                        mc.getRenderManager().viewerPosX,
                        mc.getRenderManager().viewerPosY,
                        mc.getRenderManager().viewerPosZ);

        RenderUtil.prepare();
        GL11.glShadeModel(GL11.GL_SMOOTH);
        RenderUtil.line3d(ActiveRenderInfo.getCameraPosition(), vec, 1f, color);
        if (posts.value) {
            RenderUtil.line3d(vec, vec.add(0, e.height, 0), 1f, color);
        }
        RenderUtil.release();
    }

    @Register
    public final Listener<ModeChangeEvent> modeChangeListener = event -> {
        setMetadata(ChatFormatting.WHITE + "[" + event.newMode + "]" + ChatFormatting.RESET);
    };
}
