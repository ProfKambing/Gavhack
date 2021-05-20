package me.gavin.gavhack.setting;

import me.gavin.gavhack.module.Module;

public class ModeSetting extends Setting {

    private int modeIndex = 0;
    private final String[] modes;

    public ModeSetting(Module module, String name, String defaultMode, String... modes) {
        super(module, name);
        this.modes = modes;
        this.modeIndex = getIndex(defaultMode);
    }

    public void cycle(boolean backwards) {
        if (!backwards) {
            if (modeIndex != modes.length - 1) {
                modeIndex++;
            } else {
                modeIndex = 0;
            }
        } else {
            if (modeIndex != 0) {
                modeIndex--;
            } else {
                modeIndex = modes.length - 1;
            }
        }
    }

    private int getIndex(String name) {
        for (int i = 0; i < modes.length; i++) {
            if (modes[i].equals(name))
                return i;
        }

        return -1;
    }

    public String getMode() {
        return modes[modeIndex];
    }
}
