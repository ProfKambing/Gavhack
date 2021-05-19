package me.gavin.gavhack.manager;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.module.impl.AntiFog;
import me.gavin.gavhack.module.impl.ClickGUI;
import me.gavin.gavhack.module.impl.ColorModule;
import me.gavin.gavhack.module.impl.Fullbright;

import java.util.ArrayList;

public class ModuleManager {

    public final ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();
        registerModules();
    }

    public void registerModules() {
        // Combat

        // Movement

        // Render
        this.modules.add(new AntiFog());
        this.modules.add(new Fullbright());

        // Player

        // Client
        this.modules.add(new ClickGUI());
        this.modules.add(new ColorModule());
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
}
