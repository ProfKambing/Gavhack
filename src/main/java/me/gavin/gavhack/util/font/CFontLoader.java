package me.gavin.gavhack.util.font;

import java.awt.Font;

public class CFontLoader {

    public static Font MULI_SEMIBOLD = getFontByName("muli-semibold").deriveFont(18f);

    public static Font getFontByName(String name) {
        if (name.equalsIgnoreCase("muli-semibold")) {
            return getFontFromInput("/assets/gavhack/Muli-SemiBold.ttf");
        }

        return null;
    }

    public static Font getFontFromInput(String path) {

        try {
            Font newFont = Font.createFont(Font.TRUETYPE_FONT, CFontLoader.class.getResourceAsStream(path));
            return newFont;
        }
        catch (Exception e) {
            return null;
        }
    }
}
