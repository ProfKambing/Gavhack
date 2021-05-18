package me.gavin.gavhack.setting;

public class ModeSetting extends Setting {

    private int modeIndex = 0;
    private final String[] modes;

    public ModeSetting(String name, String defaultMode, String... modes) {
        super(name);
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
}
