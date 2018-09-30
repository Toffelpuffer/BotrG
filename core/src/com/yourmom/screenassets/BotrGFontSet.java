package com.yourmom.screenassets;


import static com.yourmom.game.BotrGStyle.screenHeight;

/** Stores fonts for every 'text-field-category' on screen.
 * Created by Ben on 15.07.2018.
 */

public class BotrGFontSet {
    private BotrGFont buttonFont;
    private BotrGFont dialogueBoxFont;
    private BotrGFont hpInfoBoxFontN;
    private BotrGFont hpInfoBoxFontS;


    public BotrGFontSet(String fontName){

        buttonFont = new BotrGFont(fontName, (int)(screenHeight * 0.03125f));
        dialogueBoxFont = new BotrGFont(fontName, (int)(screenHeight * 0.0285f));
        hpInfoBoxFontN = new BotrGFont(fontName, (int)(screenHeight * 0.03f));
        hpInfoBoxFontS = new BotrGFont(fontName, (int)(screenHeight * 0.025f));
    }

    public BotrGFont getButtonFont(){
        return buttonFont;
    }

    public BotrGFont getDialogueBoxFont() {
        return dialogueBoxFont;
    }

    public BotrGFont getHpInfoBoxFontN() {
        return hpInfoBoxFontN;
    }
    public BotrGFont getHpInfoBoxFontS() {
        return hpInfoBoxFontS;
    }
}
