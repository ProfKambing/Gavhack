package me.gavin.gavhack.setting;

import me.gavin.gavhack.module.Module;

public class NumberSetting extends Setting {

    public float value;
    public final float min, max, increment;

    public NumberSetting(Module module, String name, float value, float min, float max, float increment) {
        super(module, name);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public void setClamped(float value) {
        float precision = 1.0f / this.increment;
        this.value = Math.round(Math.max(this.min, Math.min(this.max, value)) * precision) / precision;
    }
}
