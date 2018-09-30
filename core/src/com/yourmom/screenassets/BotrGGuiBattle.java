package com.yourmom.screenassets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.yourmom.game.BotrGStyle;

/**Retains the screen tiles and buttons for the GUI used in the battle scene.
 * Created by Ben on 13.07.2018.
 */

public class BotrGGuiBattle {
    private final static int LINE_COUNT = 4;
    private BotrGTile guiBg;
    public BotrGTile arrow;
    public BotrGButton[] buttons;
    private float[] lineHeights;
    private BotrGText[] lines;

    public BotrGCharsHpDisplay hpBoxes;



    BotrGGuiBattle(){
        buttons = new BotrGButton[LINE_COUNT];
        lineHeights = new float[LINE_COUNT];
        lines = new BotrGText[LINE_COUNT];

        setupGui(BotrGStyle.screenWidth, BotrGStyle.screenHeight, BotrGStyle.getActiveFontSet());

        hpBoxes = new BotrGCharsHpDisplay();
    }


    public void setButtonDisplayText(int button, String newDisplayText){
        buttons[button].setDisplayText(newDisplayText);
    }

    public void setButtonsDisplayText(String[] displayTexts){
        for(int i = 0; i < LINE_COUNT; i++){
            if(displayTexts[i].equals("") || displayTexts[i].equals(" ")){
                buttons[i].setDisplayText("");
                buttons[i].setInactive();
            } else {
                buttons[i].setActive();
                buttons[i].setDisplayText(displayTexts[i]);
            }
        }
    }

    public void setButtonsInactive(){
        for(int i = 0; i < LINE_COUNT; i++){
            buttons[i].setDisplayText("");
            buttons[i].setInactive();
        }
    }

    public void setLine(int line, String newText){
        lines[line].setDisplayedText(newText);
    }

    public void setLines(String[] newLines){
        for(int i = 0; i < LINE_COUNT; i++){
            lines[i].setDisplayedText(newLines[i]);
        }
    }

    public void setLines(String newLines){
        String[] setLines = BotrGTextConvertor.stringToLines(newLines);
        for(int i = 0; i < LINE_COUNT; i++){
            lines[i].setDisplayedText(setLines[i]);
        }
    }

    public void resetLines(){
        for(int i = 0; i < LINE_COUNT; i++){
            lines[i].setDisplayedText("");
        }
    }

    public boolean buttonPressed(){
        for(BotrGButton button: buttons)
            if(button.pressed) return true;
        return false;
    }

    public void update(){
        hpBoxes.update();
        for(int i = 0; i < LINE_COUNT; i++){
            buttons[i].update();
        }
    }

    public void draw(Batch batch){
        guiBg.draw(batch);
        hpBoxes.draw(batch);
        for(int i= 0; i < LINE_COUNT; i++){
            buttons[i].draw(batch);
            lines[i].draw(batch, BotrGStyle.getActiveFontSet().getDialogueBoxFont());
        }
        arrow.draw(batch);
    }

    private void setupGui(float screenWidth, float screenHeight, BotrGFontSet fontSet){
        guiBg = new BotrGTile(0,0, BotrGSpriteArchive.getSprite(9000), (int)screenWidth, (int)(screenHeight/2), CenterType.DEFAULT);

        buttons[0] = new BotrGButton(screenWidth * 0.256f,  screenHeight * 0.1875f, 9001, 9002, (int)(screenWidth * 0.4444f), (int)(screenHeight * 0.0978f), "NOT_SET");
        buttons[1] = new BotrGButton(screenWidth * 0.7439f, screenHeight * 0.1875f, 9001, 9002, (int)(screenWidth * 0.4444f), (int)(screenHeight * 0.0978f), "NOT_SET");
        buttons[2] = new BotrGButton(screenWidth * 0.256f,  screenHeight * 0.0679f, 9001, 9002, (int)(screenWidth * 0.4444f), (int)(screenHeight * 0.0978f), "NOT_SET");
        buttons[3] = new BotrGButton(screenWidth * 0.7439f, screenHeight * 0.0679f, 9001, 9002, (int)(screenWidth * 0.4444f), (int)(screenHeight * 0.0978f), "NOT_SET");

        float linesStartWidth = screenWidth * 0.055f;

        lineHeights[0] = screenHeight * 0.445f;
        lineHeights[1] = screenHeight * 0.395f;
        lineHeights[2] = screenHeight * 0.345f;
        lineHeights[3] = screenHeight * 0.295f;

        for(int i = 0; i < LINE_COUNT; i++){
            lines[i] = new BotrGText("", linesStartWidth, lineHeights[i]);
        }

        arrow = new BotrGTile(screenWidth * .91f, screenHeight * .265f, BotrGSpriteArchive.getSprite(9006), (int)(screenWidth * .0531f), (int)(screenWidth * .0338f), CenterType.DEFAULT);
        arrow.setAlpha(0.0f);
    }
}
