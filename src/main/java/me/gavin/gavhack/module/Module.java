package me.gavin.gavhack.module;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.event.MetaDataSetEvent;
import me.gavin.gavhack.setting.KeybindSetting;
import me.gavin.gavhack.setting.Setting;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public abstract class Module {

    protected final Minecraft mc = Minecraft.getMinecraft();
    protected final Gavhack gavhack = Gavhack.INSTANCE;
    public final ArrayList<Setting> settings = new ArrayList<>();

    public final String name;
    public final String description;
    public final Category category;

    public boolean enabled;

    private String currentMetadata;

    public final KeybindSetting keybind;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;

        this.keybind = new KeybindSetting(this, "Bind", 0);
        this.settings.add(keybind);
    }

    public void toggle() {
        if (enabled) disable(); else enable();
    }

    public void enable() {
        enabled = true;
        gavhack.eventSys.subscribe(this);
        onEnable();
    }

    public void disable() {
        gavhack.eventSys.unsubscribe(this);
        enabled = false;
        onDisable();
    }

    protected void onEnable() { }

    protected void onDisable() { }

    protected void setMetadata(String text) {
        this.currentMetadata = text;
        gavhack.eventSys.post(new MetaDataSetEvent(this, text));
    }

    public String getMetaData() {
        if (currentMetadata == null) {
            return "";
        } else {
            return currentMetadata;
        }
    }
}
