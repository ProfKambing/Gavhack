package me.gavin.gavhack.event;

import me.gavin.gavhack.module.Module;

public class MetaDataSetEvent {

    public final Module module;
    public final String metadata;

    public MetaDataSetEvent(Module module, String metadata) {
        this.module = module;
        this.metadata = metadata;
    }
}
