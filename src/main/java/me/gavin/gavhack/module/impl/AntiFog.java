package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import org.lwjgl.input.Keyboard;

public class AntiFog extends Module {
    public AntiFog() {
        super("AntiFog", "Removes fog", Category.Render);
    }

    // See me.gavin.gavhack.mixin.EntityRendererMixin around line 18
}
