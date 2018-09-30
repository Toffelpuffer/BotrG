package com.yourmom.events;


import com.badlogic.gdx.Gdx;
import com.yourmom.animation.AnimText;
import com.yourmom.screenassets.BotrGSceneBattle;

/**
 * Created by Ben on 16.07.2018.
 */

public class BotrGEventPlayerChoice implements BotrGEvent {

    private AnimText animText;

    @Override
    public void startEvent(BotrGSceneBattle battleScene) {
        animText = new AnimText("What will " + battleScene.getPlayerChar().getName() + " do?");
        //battleScene.getGui().setLines("CHOOSE YOUR ACTION:");
        battleScene.getGui().setButtonsDisplayText(new String[] {"Attack","","",""});

        animText.startAnimation(battleScene);
        battleScene.setPlayerIsAttacking(true);
    }

    @Override
    public void update(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        //if(animText.isActive() && Gdx.input.justTouched()) animText.endAnimation(battleScene);

        if(battleScene.getGui().buttons[0].pressed){

            animText.endAnimation(battleScene);
            battleScene.getGui().setButtonsInactive();



            endEvent(battleScene, eventCoor);
        }
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {
        animText.updateAnimation(battleScene);
    }

    @Override
    public void endEvent(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        battleScene.getGui().resetLines();

        eventCoor.setNextEvent(new BotrGEventAttackChoice());
        eventCoor.nextEvent();
    }
}
