package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import org.lwjgl.input.Keyboard;

public class Fullbright extends Module {
    public Fullbright() {
        super("Fullbright", "Makes everything bright", Category.Render);
    }

    // see me.gavin.gavhack.mixin.EntityRendererMixin for this patch
}
