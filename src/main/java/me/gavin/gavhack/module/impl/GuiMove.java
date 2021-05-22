package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;

public class GuiMove extends Module {

    public GuiMove() {
        super("GuiMove", "Move while in gui's", Category.Movement);
    }

    // see me.gavin.gavhack.mixin.MovementInputFromOptionsMixin
    // gerald0mc on top
}
