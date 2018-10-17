package com.yourmom.animation.attacks;

import com.yourmom.animation.BotrGAnimation;
import com.yourmom.screenassets.BotrGSceneBattle;

/**
 * Created by Ben on 30.07.2018.
 */

public class AnimReduceHpBar extends BotrGAnimation {
    private boolean animActive;
    private int frameCnt;
    private int delay;
    private final int ANIM_LENGTH;

    private boolean harmedCharIsPlayer;
    private double speed;
    private int reduceFrames = 30;
    private double finalWidthPerc;
    private double currentWidthPerc;

    public AnimReduceHpBar(BotrGSceneBattle battleScene, int dealtDmg, float newSpeed, boolean harmedCharIsPlayer){
        animActive = false;
        frameCnt = 0;
        delay = 0;

        this.harmedCharIsPlayer = harmedCharIsPlayer;

        finalWidthPerc = (double)(battleScene.getChar(this.harmedCharIsPlayer).getHp() - dealtDmg) / battleScene.getChar(this.harmedCharIsPlayer).getBaseHp();
        if(finalWidthPerc < 0) finalWidthPerc = 0.0d;
        System.out.println("ANIM_RED_HPBAR: finalWidthPerc: " + finalWidthPerc);

        currentWidthPerc = (double)battleScene.getChar(this.harmedCharIsPlayer).getHp() / battleScene.getChar(this.harmedCharIsPlayer).getBaseHp();
        System.out.println("ANIM_RED_HPBAR: currentWidthPerc: " + currentWidthPerc);


        if(newSpeed == 0){
            speed = (double)dealtDmg / (reduceFrames * battleScene.getChar(this.harmedCharIsPlayer).getBaseHp());
            System.out.println("ANIM_RED_HPBAR: Speed: " + speed + " DealtDmg: " + dealtDmg + " ReduceFrames: " + reduceFrames + " currentHp: " + battleScene.getDefendingChar().getHp());
            ANIM_LENGTH = reduceFrames + 1;

        } else {
            speed = (newSpeed / 100);

            ANIM_LENGTH = (int)(finalWidthPerc / speed) + 1;
        }
    }

    @Override
    public void startAnimation(BotrGSceneBattle battleScene) {
        animActive = true;
        frameCnt = -delay;
        System.out.println("ANIM_RED_HPBAR: AnimReduceHpBar started!");
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {
        if(frameCnt == ANIM_LENGTH) endAnimation(battleScene);

        if(animActive){
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
    }

    @Override
    public void endAnimation(BotrGSceneBattle battleScene) {
        battleScene.getAttackingChar().resetPos(battleScene.getStage());
        battleScene.getDefendingChar().resetPos(battleScene.getStage());
        animActive = false;
        System.out.println("ANIM_RED_HPBAR: HitAnim ended!");
    }

    @Override
    public boolean isActive() {
        return animActive;
    }

    @Override
    public void setDelay(int delayedFrames) {
        delay = delayedFrames;
    }

    public int getANIM_LENGTH(){
        return ANIM_LENGTH;
    }
}
