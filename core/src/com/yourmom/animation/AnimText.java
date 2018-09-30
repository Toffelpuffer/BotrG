package com.yourmom.animation;

import com.yourmom.screenassets.BotrGSceneBattle;
import com.yourmom.screenassets.BotrGTextConvertor;

/**
 * Created by Ben on 18.07.2018.
 */

public class AnimText extends BotrGAnimation {

    private boolean animationActive;
    private int animationLength;
    private int frameCnt;
    private int delay;
    private int displayedLines;
    private int displayedCharsInLine;
    private String[] dialogueText;


    public AnimText(String displayedText){
        dialogueText = BotrGTextConvertor.stringToLines(displayedText);
        animationLength = 0;
        for(int i = 0; i < dialogueText.length; i++){
            animationLength += dialogueText[i].length();
        }
        animationActive = false;
        delay = 0;
    }

    @Override
    public void startAnimation(BotrGSceneBattle battleScene) {
        battleScene.getGui().resetLines();
        frameCnt = delay * -1;
        displayedLines = 0;
        displayedCharsInLine = 1;
        animationActive = true;
        //System.out.println("TextAnim Start");
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {
        if(frameCnt == animationLength && frameCnt > 0) endAnimation(battleScene);

        if(animationActive && frameCnt >= 0) {

            if (displayedCharsInLine > dialogueText[displayedLines].length()) {
                displayedLines++;
                displayedCharsInLine = 1;
            }
            battleScene.getGui().setLine(displayedLines, String.copyValueOf(dialogueText[displayedLines].toCharArray(), 0, displayedCharsInLine));

            frameCnt++;
            displayedCharsInLine++;

        }

        if(animationActive && frameCnt < 0) frameCnt++;
    }

    @Override
    public void endAnimation(BotrGSceneBattle battleScene) {
        battleScene.getGui().setLines(dialogueText);
        frameCnt = 0;
        animationActive = false;
        //System.out.println("TextAnim End");
    }

    @Override
    public boolean isActive() {
        return animationActive;
    }

    @Override
    public void setDelay(int delayedFrames) {
        delay = delayedFrames;
    }

    public void setNewText(String displayedText){
        dialogueText = BotrGTextConvertor.stringToLines(displayedText);
        animationLength = 0;
        for(int i = 0; i < dialogueText.length; i++){
            animationLength += dialogueText[i].length();
        }
        animationActive = false;
    }
}