package me.gavin.gavhack.clickgui.api;

public abstract class AbstractDragComponent extends AbstractComponent {

    public int dragX, dragY;
    public boolean dragging;

    public AbstractDragComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);

    public abstract void mouseReleased(int mouseX, int mouseY, int mouseButton);

    @Override
    public abstract void render(int mouseX, int mouseY, float partialTicks);

    public void startDragging(int mouseX, int mouseY) {
        dragging = true;
        dragX = mouseX - x;
        dragY = mouseY - y;
    }

    public void stopDragging() {
        dragging = false;
    }

    public void updateDragLogic(int mouseX, int mouseY) {
        if (dragging) {
            this.x = (mouseX - dragX);
            this.y = (mouseY - dragY);
        }
    }
}