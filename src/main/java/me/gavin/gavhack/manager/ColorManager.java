package me.gavin.gavhack.manager;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.module.impl.ColorModule;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ColorManager {

    private float r, g, b;

    private ColorModule colorModule;

    public ColorManager() {
        this.colorModule = (ColorModule) Gavhack.INSTANCE.moduleManager.getModule(ColorModule.class);
        updateColor();
    }

    public void updateColor() {
        if (colorModule.rainbow.value) {
            Color rainbow = getRainbow(colorModule.speed.value, colorModule.saturation.value);
            this.r = rainbow.getRed();
            this.g = rainbow.getGreen();
            this.b = rainbow.getBlue();
        } else {
            this.r = colorModule.red.value;
            this.g = colorModule.green.value;
            this.b = colorModule.blue.value;
        }
    }

    public Color getRainbow(float time, float saturation) {
        float hue = (System.currentTimeMillis() % (int) (time * 1000)) / (time * 1000);
        return new Color(Color.HSBtoRGB(hue, saturation, 1f));
    }

    public float getR() {
        return this.r;
    }

    public float getG() {
        return this.g;
    }

    public float getB() {
        return this.b;
    }

    public Color asColor() {
        return new Color((int)r, (int)g, (int)b);
    }

    public void glColor() {
        GL11.glColor3f(r / 255f, g / 255f, b / 255f);
    }
}
