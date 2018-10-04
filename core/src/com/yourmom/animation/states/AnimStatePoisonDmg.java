package com.yourmom.animation.states;

import com.yourmom.animation.BotrGAnimation;
import com.yourmom.screenassets.BotrGSceneBattle;

import static com.yourmom.game.BotrGStyle.screenWidth;

/**
 * Created by Ben on 8/25/2018.
 */

public class AnimStatePoisonDmg extends BotrGAnimation {
    private boolean animActive;
    private int frameCnt;
    private int delay;
    private boolean isPlayer;
    private final int ANIM_LENGTH = 20;
    private final float MOVELENGTH = (20.0f / 1440.0f) * screenWidth;
    private final int MOVEFRAMES = 3;

    public AnimStatePoisonDmg(boolean playAnimOnPlayer){
        animActive = false;
        frameCnt = 0;
        delay = 0;
        isPlayer = playAnimOnPlayer;

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

        //TODO: add animation
            if(frameCnt < MOVEFRAMES ||
                    (frameCnt >= MOVEFRAMES * 2 && frameCnt < MOVEFRAMES * 3) ||
                    (frameCnt >= MOVEFRAMES * 4 && frameCnt < MOVEFRAMES * 5)){
                battleScene.getChar(isPlayer).setXPos(
                        battleScene.getChar(isPlayer).getXPos() - MOVELENGTH);
            }
            if((frameCnt >= MOVEFRAMES && frameCnt < MOVEFRAMES * 2) ||
                    (frameCnt >= MOVEFRAMES * 3 && frameCnt < MOVEFRAMES * 4) ||
                    (frameCnt >= MOVEFRAMES * 5 && frameCnt < MOVEFRAMES * 6)){
                battleScene.getChar(isPlayer).setXPos(
                        battleScene.getChar(isPlayer).getXPos() + MOVELENGTH);
            }

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
