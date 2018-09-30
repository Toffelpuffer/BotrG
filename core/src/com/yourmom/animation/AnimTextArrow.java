package com.yourmom.animation;

import com.yourmom.screenassets.BotrGSceneBattle;
import com.yourmom.screenassets.BotrGTile;

/**
 * Created by Ben on 24.07.2018.
 */

public class AnimTextArrow extends BotrGAnimation {

    private int frameCnt;
    private boolean animationActive;
    private final int ANIM_FRAMES_VISIBLE = 30;


    public AnimTextArrow(){
        frameCnt = 0;
        animationActive = false;
    }

    @Override
    public void startAnimation(BotrGSceneBattle battleScene) {
        animationActive = true;
        frameCnt = 0;
        //System.out.println("Arrow Start");
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {
        if(animationActive){
            if(frameCnt == 0) battleScene.getGui().arrow.setAlpha(1.0f);
            if(frameCnt == ANIM_FRAMES_VISIBLE){
                battleScene.getGui().arrow.setAlpha(0.0f);
            }

            frameCnt++;
            if(frameCnt == ANIM_FRAMES_VISIBLE * 2) frameCnt = 0;
        }
    }

    @Override
    public void endAnimation(BotrGSceneBattle battleScene) {
        animationActive = false;
        battleScene.getGui().arrow.setAlpha(.0f);
        //System.out.println("Arrow End");
    }

    @Override
    public boolean isActive() {
        return animationActive;
    }

    @Override
    public void setDelay(int delayedFrames) {

    }
}
