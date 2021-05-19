package me.gavin.gavhack.clickgui.api;

import java.util.ArrayList;

public abstract class AbstractPanelComponent extends AbstractOffsettable implements ITypeable, IReleaseable {

    public ArrayList<AbstractOffsettable> settingComponents;

    public AbstractPanelComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
        settingComponents = new ArrayList<>();
    }

    public abstract int getSettingHeight();
}
