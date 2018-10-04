package com.yourmom.animation.attacks;

import com.badlogic.gdx.Gdx;
import com.yourmom.animation.BotrGAnimation;
import com.yourmom.screenassets.BotrGSceneBattle;

/**
 * Created by Ben on 30.07.2018.
 */

public class AnimHit extends BotrGAnimation {
    private boolean animActive;
    private int frameCnt;
    private int delay;
    private final int ANIM_LENGTH = 200;

    private boolean harmedCharIsPlayer;
    private int state;
    private AnimReduceHpBar animReduceHpBar;

    public AnimHit(BotrGSceneBattle battleScene, int dealtDmg, float newSpeed, boolean harmedCharIsPlayer){
        animActive = false;
        frameCnt = 0;
        delay = 0;

        this.harmedCharIsPlayer = harmedCharIsPlayer;

        animReduceHpBar = new AnimReduceHpBar(battleScene, dealtDmg, newSpeed, harmedCharIsPlayer);
    }

    @Override
    public void startAnimation(BotrGSceneBattle battleScene) {
        animActive = true;
        frameCnt = -delay;
        state = 0;
        System.out.println("ANIM_HIT: HitAnim started!");
        animReduceHpBar.startAnimation(battleScene);
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {
        if(frameCnt == ANIM_LENGTH) endAnimation(battleScene);

        if(animActive && state == 0) {
            if (frameCnt == 0 || frameCnt == 4 || frameCnt == 8 || frameCnt == 12)
                battleScene.getChar(harmedCharIsPlayer).getCharTile().setAlpha(0.0f);
            else if (frameCnt == 2 || frameCnt == 6 || frameCnt == 10 || frameCnt == 14)
                battleScene.getChar(harmedCharIsPlayer).getCharTile().setAlpha(1.0f);
            else if (frameCnt > 14) state = 1;
        }

        if(animActive && state == 1){
            animReduceHpBar.updateAnimation(battleScene);
            if(!animReduceHpBar.isActive()){
                endAnimation(battleScene);
            }
        }
        
        if(animActive) frameCnt++;
        //if(Gdx.input.justTouched()) endAnimation(battleScene);
    }

    @Override
    public void endAnimation(BotrGSceneBattle battleScene) {
        battleScene.getAttackingChar().getCharTile().setAlpha(1.0f);
        battleScene.getDefendingChar().getCharTile().setAlpha(1.0f);
        battleScene.getAttackingChar().resetPos(battleScene.getStage());
        battleScene.getDefendingChar().resetPos(battleScene.getStage());
        animActive = false;
        System.out.println("ANIM_HIT: HitAnim ended!");
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
