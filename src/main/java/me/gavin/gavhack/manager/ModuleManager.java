package me.gavin.gavhack.manager;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.event.MetaDataSetEvent;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.module.impl.*;
import me.gavin.quasar.Listener;

import java.util.ArrayList;

public class ModuleManager {

    public final ArrayList<Module> modules;
    public final ArrayList<Module> sortedModules;

    public final Listener<MetaDataSetEvent> eventListener;

    public ModuleManager() {
        modules = new ArrayList<>();
        sortedModules = new ArrayList<>();
        registerModules();

        eventListener = event -> {
            this.sortedModules.sort(this::sortByLength);
        };
        Gavhack.INSTANCE.eventSys.subscribe(eventListener);
    }

    public void registerModules() {
        // Combat

        // Movement
        this.modules.add(new Sprint());
        this.modules.add(new Flight());

        // Render
        this.modules.add(new AntiFog());
        this.modules.add(new Fullbright());

        // Player

        // Client
        this.modules.add(new ClickGUI());
        this.modules.add(new HUDEditor());
        this.modules.add(new ColorModule());

        this.sortedModules.addAll(modules);
        this.sortedModules.sort(this::sortByLength);
    }

    public Module getModule(Class<? extends Module> clazz) {
        for (Module m : modules) {
            if (m.getClass() == clazz)
                return m;
        }

        return null;
    }

    public ArrayList<Module> getModulesFromCategory(Category category) {
        final ArrayList<Module> list = new ArrayList<>();
        for (Module m : modules) {
            if (m.category == category)
                list.add(m);
        }

        return list;
    }

    private int sortByLength(Module m1, Module m2) {
        return Integer.compare(Gavhack.INSTANCE.fontRenderer.getStringWidth(m2.name + m2.getMetaData()), Gavhack.INSTANCE.fontRenderer.getStringWidth(m1.name + m1.getMetaData()));
    }
}
