package me.gavin.gavhack.clickgui.impl.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time extends HUDComponent {
    public Time() {
        super("Time", "Shows the time on your pc.", 100, Gavhack.INSTANCE.fontRenderer.getHeight());
    }

    private String text = "Time: ";

    @Override
    public void drawInHud() {
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(text, x, y, Gavhack.INSTANCE.colorManager.asColor());
    }

    @Override
    public void onUpdate() {
        String time = new SimpleDateFormat("h:dd").format(new Date());
        text = "Time: " + ChatFormatting.WHITE + time;

        this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(text);
    }
}
