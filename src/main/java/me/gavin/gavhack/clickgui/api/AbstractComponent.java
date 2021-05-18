package me.gavin.gavhack.clickgui.api;

public abstract class AbstractComponent extends Area implements IClickable, IDrawable{
    public AbstractComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    @Override
    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);

    @Override
    public abstract void render(int mouseX, int mouseY, float partialTicks);
}
