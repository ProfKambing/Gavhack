package me.gavin.gavhack.module;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.setting.KeybindSetting;
import me.gavin.gavhack.setting.Setting;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public abstract class Module {

    protected final Minecraft mc = Minecraft.getMinecraft();
    protected final Gavhack gavhack = Gavhack.INSTANCE;
    protected final ArrayList<Setting> settings = new ArrayList<>();

    public final String name;
    public final String description;
    public final Category category;

    public boolean enabled;

    public KeybindSetting keybind;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;

        this.keybind = new KeybindSetting("Keybind", 0);
        this.settings.add(keybind);
    }

    public void toggle() {
        if (enabled) disable(); else enable();
    }

    public void enable() {
        enabled = true;
        onEnable();
        gavhack.eventSys.subscribe(this);
    }

    public void disable() {
        gavhack.eventSys.unsubscribe(this);
        onDisable();
        enabled = false;
    }

    protected void onEnable() { }

    protected void onDisable() { }
}
