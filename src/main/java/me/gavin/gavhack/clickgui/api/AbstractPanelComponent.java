package me.gavin.gavhack.clickgui.api;

import java.util.ArrayList;

public abstract class AbstractPanelComponent extends AbstractComponent implements ITypeable {

    public int yOffset = 0;

    public AbstractPanelComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract int getSettingHeight();
}
