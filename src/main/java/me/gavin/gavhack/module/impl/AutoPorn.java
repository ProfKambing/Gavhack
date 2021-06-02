package me.gavin.gavhack.module.impl;

import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import me.gavin.gavhack.setting.ModeSetting;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class AutoPorn extends Module {
    public AutoPorn() {
        super("AutoPorn", ";)", Category.Misc);
        settings.add(mode);
    }

    public final ModeSetting mode = new ModeSetting(this, "Porn Mode", "Hentai", "Hentai", "Femboy", "BBC", "Gay", "Trans", "Straight", "Lesbian", "Underage");

    @Override
    public void onEnable() {
        if(mode.getMode().equals("Hentai")) {
            try {
                Desktop.getDesktop().browse(URI.create("https://www.pornhub.com/video/search?search=hentai"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else if(mode.getMode().equals("Femboy")) {
            try {
                Desktop.getDesktop().browse(URI.create("https://www.pornhub.com/video/search?search=femboy"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else if(mode.getMode().equals("BBC")) {
            try {
                Desktop.getDesktop().browse(URI.create("https://www.pornhub.com/video/search?search=bbc"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else if(mode.getMode().equals("Gay")) {
            try {
                Desktop.getDesktop().browse(URI.create("https://www.pornhub.com/video/search?search=gay"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else if(mode.getMode().equals("Trans")) {
            try {
                Desktop.getDesktop().browse(URI.create("https://www.pornhub.com/video/search?search=trans"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else if(mode.getMode().equals("Straight")) {
            try {
                Desktop.getDesktop().browse(URI.create("https://www.pornhub.com/video/search?search=straight"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else if(mode.getMode().equals("Lesbian")) {
            try {
                Desktop.getDesktop().browse(URI.create("https://www.pornhub.com/video/search?search=lesbian"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else if(mode.getMode().equals("Underage")) {
            try {
                Desktop.getDesktop().browse(URI.create("https://www.youtube.com/watch?v=XeDM1ZjMK50"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        toggle();
    }
}
