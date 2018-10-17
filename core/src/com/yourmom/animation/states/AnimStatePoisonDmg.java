package com.yourmom.animation.states;

import com.yourmom.animation.BotrGAnimation;
import com.yourmom.animation.attacks.AnimReduceHpBar;
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
    private AnimReduceHpBar animRedHp;

    private final float MOVELENGTH = (15.0f / 1440.0f) * screenWidth;
    private final int MOVEFRAMES = 3;
    private final int SHAKE_LENGTH = MOVEFRAMES * 6;
    private final float COLOR_FRAMES = (SHAKE_LENGTH) / 2;
    private final float[] FINAL_COLOR = {85.0f / 255.0f, 26.0f / 255.0f, 139.0f / 255.0f}; //85,26,139
    private float[] currentColor;
    private final int ANIM_LENGTH;

    public AnimStatePoisonDmg(BotrGSceneBattle battleScene, int dealtDmg, boolean playAnimOnPlayer){
        animActive = false;
        frameCnt = 0;
        delay = 0;
        isPlayer = playAnimOnPlayer;
        currentColor = new float[]{1.0f, 1.0f, 1.0f};

        animRedHp = new AnimReduceHpBar(battleScene, dealtDmg, 0, playAnimOnPlayer);

        ANIM_LENGTH = SHAKE_LENGTH + animRedHp.getANIM_LENGTH();
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

            if(frameCnt < COLOR_FRAMES){
                for(int i = 0; i < currentColor.length; i++)
                    currentColor[i] -= (1.0f - FINAL_COLOR[i]) / COLOR_FRAMES;
            }
            if(frameCnt >= COLOR_FRAMES && frameCnt < SHAKE_LENGTH){
                for(int i = 0; i < currentColor.length; i++)
                    currentColor[i] += (1.0f - FINAL_COLOR[i]) / COLOR_FRAMES;
            }

            battleScene.getChar(isPlayer).getCharTile().setColor(
                    currentColor[0],
                    currentColor[1],
                    currentColor[2],
                    1.0f
                );

            if(frameCnt >= SHAKE_LENGTH){
                if(frameCnt == SHAKE_LENGTH) animRedHp.startAnimation(battleScene);

                animRedHp.updateAnimation(battleScene);

                //if(!animRedHp.isActive()) endAnimation(battleScene);
            }

            frameCnt++;
        } else
        if(animActive && frameCnt < 0) frameCnt++;
    }

    @Override
    public void endAnimation(BotrGSceneBattle battleScene) {
        battleScene.getAttackingChar().resetPos(battleScene.getStage());
        battleScene.getDefendingChar().resetPos(battleScene.getStage());
        battleScene.getChar(isPlayer).getCharTile().resetColor();

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
