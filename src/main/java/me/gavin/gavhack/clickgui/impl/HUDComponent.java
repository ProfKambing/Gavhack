package me.gavin.gavhack.clickgui.impl;

import me.gavin.gavhack.clickgui.api.AbstractDragComponent;

public abstract class HUDComponent extends AbstractDragComponent {

    public boolean visible;
    public String name;
    public String description;

    public HUDComponent(String name, String description, int width, int height) {
        super(10, 10, width, height);
        this.name = name;
        this.description = description;
    }

    public abstract void drawInHud();
}
