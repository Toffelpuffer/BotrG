package com.yourmom.events;

import com.badlogic.gdx.Gdx;
import com.yourmom.animation.AnimText;
import com.yourmom.screenassets.BotrGSceneBattle;

/**
 * Created by Ben on 16.07.2018.
 */

public class BotrGEventAttackChoice implements BotrGEvent {

    private AnimText animText;
    private int nextAttack = 0;

    @Override
    public void startEvent(BotrGSceneBattle battleScene) {
        animText = new AnimText("Choose your attack:");
        battleScene.getGui().setButtonsDisplayText(battleScene.getPlayerChar().getAttackNames());

        animText.startAnimation(battleScene);
    }

    @Override
    public void update(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        if(battleScene.getGui().buttonPressed()){

            battleScene.getGui().setButtonsInactive();

            for(int i = 0; i < 4; i++){
                if(battleScene.getGui().buttons[i].pressed){
                    nextAttack = i;
                    break;
                }
            }

            animText.endAnimation(battleScene);

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

        eventCoor.setNextEvent(new BotrGEventStatusCheck(nextAttack));
        eventCoor.nextEvent();
    }
}
