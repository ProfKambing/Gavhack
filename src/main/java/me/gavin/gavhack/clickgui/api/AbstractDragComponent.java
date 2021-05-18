package me.gavin.gavhack.clickgui.api;

public abstract class AbstractDragComponent extends AbstractComponent {

    public AbstractDragComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);

    @Override
    public abstract void render(int mouseX, int mouseY, float partialTicks);
}
