package me.gavin.gavhack.clickgui.impl.hud;

import me.gavin.gavhack.Gavhack;
import me.gavin.gavhack.clickgui.impl.HUDComponent;
import java.util.Calendar;

public class Welcomer extends HUDComponent {
    public Welcomer() {
        super("Welcomer", "Says hi on your screen :)", 100, Gavhack.INSTANCE.fontRenderer.getHeight());
    }

    public String text = WelcomeMessages() + " and welcome to GavHack.";

    public String playerName = mc.getSession().getUsername();

    @Override
    public void drawInHud() {
        text = WelcomeMessages() + " and welcome to GavHack.";
        Gavhack.INSTANCE.fontRenderer.drawStringWithShadow(text, x, y, Gavhack.INSTANCE.colorManager.asColor());
    }

    @Override
    public void onUpdate() {
        this.width = Gavhack.INSTANCE.fontRenderer.getStringWidth(text);
    }

    private String WelcomeMessages(){
        final int timeOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(timeOfDay < 12) {
            return "Good Morning " + playerName;
        } else if (timeOfDay < 16) {
            return "Good Afternoon " + playerName;
        } else if (timeOfDay < 21) {
            return "Good Evening " + playerName;
        } else {
            return "Good Night " + playerName;
        }
    }
}
