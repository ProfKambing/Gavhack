package me.gavin.gavhack.util.font;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FontData {

    public BufferedImage genImage(Font font, boolean aa, boolean fm, CharData[] text) {
        BufferedImage image = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 1024, 1024);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, aa ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, aa ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fm ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        FontMetrics metrics = g2d.getFontMetrics();
        return null;
    }
}
