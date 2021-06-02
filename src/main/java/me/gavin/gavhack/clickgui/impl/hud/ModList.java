package me.gavin.gavhack.clickgui.impl.hud;

import com.google.common.collect.Lists;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;
import me.gavin.gavhack.event.ModeChangeEvent;
import me.gavin.gavhack.event.PacketEvent;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.module.impl.ClickGUI;
import me.gavin.gavhack.module.impl.ColorModule;
import me.gavin.quasar.Listener;
import me.gavin.quasar.Register;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.util.List;

public class ModList extends HUDComponent {
    public ModList() {
        super("ArrayList", "Show enabled modules", 1, 1);
    }

    private boolean upsideDown = false;
    private List<Module> list;
    private ScaledResolution sr;

    @Override
    public void drawInHud() {
        sr = new ScaledResolution(mc);
        list = Gavhack.INSTANCE.moduleManager.sortedModules;

        // if its on the right side of the screen
        if (x + (width / 2) > sr.getScaledWidth() / 2) {
            int yOffset = 0;
            for (Module m : list) {
                if (m.enabled) {
                    ColorModule cm = (ColorModule) Gavhack.INSTANCE.moduleManager.getModule(ColorModule.class);
                    Color rgb = Gavhack.INSTANCE.colorManager.getRGBWave(cm.speed.value, cm.saturation.value, yOffset * 20L);
                    Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(m.getMetaData() + m.name, (x + width) - Gavhack.INSTANCE.fontRenderer.getStringWidth(m.getMetaData() + m.name), y + yOffset, rgb);
                    yOffset += Gavhack.INSTANCE.fontRenderer.getHeight() + 1;
                }
            }
        } else {
            // left side
            int yOffset = 0;
            for (Module m : list) {
                if (m.enabled) {
                    ColorModule cm = (ColorModule) Gavhack.INSTANCE.moduleManager.getModule(ColorModule.class);
                    Color rgb = Gavhack.INSTANCE.colorManager.getRGBWave(cm.speed.value, cm.saturation.value, yOffset * 20L);
                    Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(m.name + m.getMetaData(), x, y + yOffset, rgb);
                    yOffset += Gavhack.INSTANCE.fontRenderer.getHeight() + 1;
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        if (list != null || sr != null) {
            Module m = list.get(0);
            if (x + (width / 2) > sr.getScaledWidth() / 2) {
                this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(m.name + m.getMetaData()) + 1;
            } else {
                this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(m.getMetaData() + m.name) + 1;
            }

            int tempHeight = 0;
            for (Module mod : list) {
                if (mod.enabled) {
                    tempHeight += Gavhack.INSTANCE.fontRenderer.getHeight() + 1;
                }
            }



            this.height = tempHeight;
        }
    }
}
