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
    private double speed;
    private int reduceFrames = 30;
    private double finalWidthPerc;
    private double currentWidthPerc;

    public AnimHit(BotrGSceneBattle battleScene, int dealtDmg, float newSpeed, boolean harmedCharIsPlayer){
        animActive = false;
        frameCnt = 0;
        delay = 0;

        this.harmedCharIsPlayer = harmedCharIsPlayer;

        if(newSpeed == 0){
            speed = (double)dealtDmg / (reduceFrames * battleScene.getChar(this.harmedCharIsPlayer).getBaseHp());
            System.out.println("ANIM_HIT: Speed: " + speed + " DealtDmg: " + dealtDmg + " ReduceFrames: " + reduceFrames + " currentHp: " + battleScene.getDefendingChar().getHp());

        } else {
            speed = (newSpeed / 100);
        }

        finalWidthPerc = (double)(battleScene.getChar(this.harmedCharIsPlayer).getHp() - dealtDmg) / battleScene.getChar(this.harmedCharIsPlayer).getBaseHp();
        if(finalWidthPerc < 0) finalWidthPerc = 0.0d;
        System.out.println("ANIM_HIT: finalWidthPerc: " + finalWidthPerc);

        currentWidthPerc = (double)battleScene.getChar(this.harmedCharIsPlayer).getHp() / battleScene.getChar(this.harmedCharIsPlayer).getBaseHp();
        System.out.println("ANIM_HIT: currentWidthPerc: " + currentWidthPerc);
    }

    @Override
    public void startAnimation(BotrGSceneBattle battleScene) {
        animActive = true;
        frameCnt = -delay;
        state = 0;
        System.out.println("ANIM_HIT: HitAnim started!");
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
            if(currentWidthPerc - speed > finalWidthPerc){

                currentWidthPerc -= speed;

                if(harmedCharIsPlayer) battleScene.getGui().hpBoxes.setPlayerHpBarLength(currentWidthPerc);
                else battleScene.getGui().hpBoxes.setEnemyHpBarLength(currentWidthPerc);
            }
            if(currentWidthPerc - speed <= finalWidthPerc) {
                if(harmedCharIsPlayer) battleScene.getGui().hpBoxes.setPlayerHpBarLength(finalWidthPerc);
                else battleScene.getGui().hpBoxes.setEnemyHpBarLength(finalWidthPerc);
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
