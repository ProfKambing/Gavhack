package me.gavin.gavhack.event;

import me.gavin.gavhack.module.Module;

public class ModeChangeEvent {

    public final String newMode;
    public final Module module;

    public ModeChangeEvent(Module module, String text) {
        this.module = module;
        this.newMode = text;
    }
}
