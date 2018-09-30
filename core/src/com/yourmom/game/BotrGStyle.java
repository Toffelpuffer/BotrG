package com.yourmom.game;

import com.yourmom.screenassets.BotrGFontSet;

/**
 * Created by Ben on 23.07.2018.
 */

public class BotrGStyle {
    public static float screenWidth;
    public static float screenHeight;

    private static BotrGFontSet guiMcFontSet;


    private BotrGStyle(){}


    public static BotrGFontSet getActiveFontSet(){
        return guiMcFontSet;
    }

    public static void setup(float screenWidth, float screenHeigth){
        BotrGStyle.screenWidth = screenWidth;
        BotrGStyle.screenHeight = screenHeigth;

        guiMcFontSet = new BotrGFontSet("Minecraftia.ttf");
    }
}
