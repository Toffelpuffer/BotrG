package com.yourmom.screenassets;

import com.badlogic.gdx.Gdx;
import com.yourmom.animation.AnimText;
import com.yourmom.animation.AnimTextArrow;

/**
 * Created by Ben on 26.07.2018.
 */

public class BotrGDialogue {
    private enum State {CREATED, STARTED, PLAYING_TEXT, PLAYING_ARROW, OVER}
    private State currentState;
    private AnimText animText;
    private AnimTextArrow arrow;
    private int buffer;
    private final int BUFFER_LENGTH = 20;

    public BotrGDialogue(String displayedText){
        animText = new AnimText(displayedText);
        arrow = new AnimTextArrow();
        buffer = 0;

        currentState = State.CREATED;
    }

    public void startDialogue(){
        currentState = State.STARTED;
    }

    public void update(BotrGSceneBattle battleScene){
        //if arrow blinking and screen touched -> go to next TextState
        if(currentState == State.PLAYING_ARROW && Gdx.input.justTouched()){
            System.out.println("DIALOGUE: End");
            arrow.endAnimation(battleScene);
            endDialogue();
        }

        //if the text animation is over -> start blinking arrow animation
        if(currentState == State.PLAYING_TEXT && !animText.isActive() && !arrow.isActive()){
            System.out.println("DIALOGUE: Text fully displayed -> start arrow");
            animText.endAnimation(battleScene);
            arrow.startAnimation(battleScene);
            currentState = State.PLAYING_ARROW;
        }

        //if text animation active and screen touched -> skip text animation
        if(buffer == BUFFER_LENGTH && currentState == State.PLAYING_TEXT && Gdx.input.justTouched()){
            System.out.println("DIALOGUE: Text stopped -> start arrow");
            animText.endAnimation(battleScene);
            arrow.startAnimation(battleScene);
            currentState = State.PLAYING_ARROW;
        }

        if(currentState == State.PLAYING_TEXT && buffer < BUFFER_LENGTH){
            buffer++;
        }

        //if currentTextState just changed to DIALOGUE -> start text animation
        if(currentState == State.STARTED){
            System.out.println("DIALOGUE: Started");
            animText.startAnimation(battleScene);
            currentState = State.PLAYING_TEXT;
        }

        //System.out.println("DIALOGUE: UPDATED!");
    }

    public void updateAnimation(BotrGSceneBattle battleScene){
        animText.updateAnimation(battleScene);
        arrow.updateAnimation(battleScene);
        //System.out.println("DIALOGUE: anim updtated!");
    }

    public void endDialogue(){
        System.out.println("DIALOGUE: over!");
        currentState = State.OVER;
    }

    public boolean isOver(){
        if(currentState == State.OVER) return true;
        return false;
    }
}
