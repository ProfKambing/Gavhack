package me.gavin.gavhack.util;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;
import me.gavin.gavhack.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

// pass forge events into gavhack event bus
public class ForgeEventTranslator {

    public ForgeEventTranslator() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        Gavhack.INSTANCE.eventSys.post(event);
        if (Minecraft.getMinecraft().currentScreen != Gavhack.INSTANCE.hudEditor) {
            Gavhack.INSTANCE.hudEditor.panel.onUpdate();
        }
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        Gavhack.INSTANCE.projectionManager.updateMatrix();
        Gavhack.INSTANCE.eventSys.post(event);
    }

    @SubscribeEvent
    public void onRenderScreen(RenderGameOverlayEvent.Text event) {
        Gavhack.INSTANCE.eventSys.post(event);
        for (HUDComponent component : Gavhack.INSTANCE.hudEditor.components) {
            if (component.visible) {
                component.drawInHud();
            }
        }
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            for (Module m : Gavhack.INSTANCE.moduleManager.modules) {
                if (m.keybind.bind == Keyboard.getEventKey())
                    m.toggle();
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        Gavhack.INSTANCE.colorManager.updateColor();
    }
}
