package me.gavin.gavhack.clickgui.api;

public abstract class AbstractOffsettable extends AbstractComponent {

    public int yOffset = 0;

    public AbstractOffsettable(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
