package me.gavin.gavhack.setting;

public class NumberSetting extends Setting {

    public float value;
    public final float min, max, increment;

    public NumberSetting(String name, float value, float min, float max, float increment) {
        super(name);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public void setClamped(float value) {
        double precision = 1.0D / this.increment;
        this.value = (float) (Math.round(Math.max(this.min, Math.min(this.max, value)) * precision) / precision);
    }
}