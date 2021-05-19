package me.gavin.gavhack.clickgui.api;

import me.gavin.gavhack.util.Utils;

import java.util.ArrayList;

public abstract class AbstractPanel<T extends AbstractPanelComponent> extends AbstractDragComponent {

    public Area header;
    public ArrayList<T> buttons;
    public boolean open;

    public AbstractPanel(int x, int y, int width, int height, int headerHeight) {
        super(x, y, width, height);
        this.buttons = new ArrayList<>();
        this.header = new Area(x, y, width, headerHeight);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (header.isMouseInside(mouseX, mouseY)) {
            if (mouseButton == 0) {
                startDragging(mouseX, mouseY);
            } else if (mouseButton == 1) {
                open = !open;
                Utils.click();
            }
        }
        handlePanelClick(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            stopDragging();
        }
    }

    public abstract void handlePanelClick(int mouseX, int mouseY, int mouseButton);

    @Override
    public abstract void render(int mouseX, int mouseY, float partialTicks);

    public void updatePositionsAndBounds() {
        this.header.x = this.x;
        this.header.y = this.y;


        int height = header.height;
        for (T comp : buttons) {
            comp.x = this.x;
            comp.y = this.y + comp.yOffset;
            height += comp.height + comp.getSettingHeight();
        }
        this.height = height;
    }
}
