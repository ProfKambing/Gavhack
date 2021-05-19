package me.gavin.gavhack.util.font;

import java.awt.*;

public class SimpleFontRenderer {

    private final Font font;
    private final boolean antiAlias, fractionalMetrics;

    public SimpleFontRenderer(Font font, boolean antiAlias, boolean fractionalMetrics) {
        this.font = font;
        this.antiAlias = antiAlias;
        this.fractionalMetrics = fractionalMetrics;
    }

    public void drawString(String text, float x, float y, int color) {

    }
}
