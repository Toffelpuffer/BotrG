package com.yourmom.animation.states;

import com.yourmom.animation.BotrGAnimation;
import com.yourmom.screenassets.BotrGSceneBattle;

/**
 * Created by Ben on 8/25/2018.
 */

public class AnimStatePoisonDmg extends BotrGAnimation {
    private boolean animActive;
    private int frameCnt;
    private int delay;
    private final int ANIM_LENGTH = 100;

    public AnimStatePoisonDmg(){
        animActive = false;
        frameCnt = 0;
        delay = 0;
    }

    @Override
    public void startAnimation(BotrGSceneBattle battleScene) {
        animActive = true;
        frameCnt = -delay;
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {
        if(frameCnt == ANIM_LENGTH) endAnimation(battleScene);

        if(animActive && frameCnt >= 0){



            frameCnt++;
        } else
        if(animActive && frameCnt < 0) frameCnt++;
    }

    @Override
    public void endAnimation(BotrGSceneBattle battleScene) {
        battleScene.getAttackingChar().resetPos(battleScene.getStage());
        battleScene.getDefendingChar().resetPos(battleScene.getStage());
        animActive = false;
    }

    @Override
    public boolean isActive() {
        return animActive;
    }

    @Override
    public void setDelay(int delayedFrames) {
        delay = delayedFrames;
    }
}
