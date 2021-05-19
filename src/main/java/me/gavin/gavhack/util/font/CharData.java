package me.gavin.gavhack.util.font;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CharData {

    public int x, y, width, height;
    public char character;

    public CharData(char c, int x, int y, int width, int height) {
        this.character = c;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
