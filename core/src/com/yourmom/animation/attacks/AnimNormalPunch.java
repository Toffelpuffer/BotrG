package com.yourmom.animation.attacks;

import com.yourmom.animation.BotrGAnimation;
import com.yourmom.screenassets.BotrGEffectTile;
import com.yourmom.screenassets.BotrGSceneBattle;
import com.yourmom.screenassets.BotrGSpriteArchive;

import static com.yourmom.game.BotrGStyle.screenHeight;
import static com.yourmom.game.BotrGStyle.screenWidth;

/**
 * Created by Ben on 24.07.2018.
 */

public class AnimNormalPunch extends BotrGAnimation {
    private boolean animActive;
    private int frameCnt;
    private int delay;
    private boolean playerIsAttacking;
    private final int ANIM_LENGTH = 18;
    private final float MOVEDISTANCE_X = (30.0f / 1440.0f) * screenWidth;
    private final float MOVEDISTANCE_Y = (30.0f / 2560.0f) * screenHeight;

    public AnimNormalPunch(){
        animActive = false;
        frameCnt = 0;
        delay = 0;
    }

    @Override
    public void startAnimation(BotrGSceneBattle battleScene) {
        animActive = true;
        frameCnt = delay * -1;
        if(battleScene.getAttackingChar().checkIsPlayer()) playerIsAttacking = true;
        else playerIsAttacking = false;

        if(playerIsAttacking){
            battleScene.setEffects(new BotrGEffectTile[] {
                    new BotrGEffectTile(battleScene.getStage().getEnemyX(), battleScene.getStage().getEnemyY() + 300f,
                            BotrGSpriteArchive.getSprite(7000),(int)(screenWidth * 0.17222f), (int)(screenWidth * 0.17222f)),
                    new BotrGEffectTile(battleScene.getStage().getEnemyX() + screenWidth * 0.00347f, battleScene.getStage().getEnemyY() + 296f,
                            BotrGSpriteArchive.getSprite(7001),(int)(screenWidth * 0.13333f), (int)(screenWidth * 0.13333f))
            });
        } else {
            battleScene.setEffects(new BotrGEffectTile[] {
                    new BotrGEffectTile(battleScene.getStage().getPlayerX(), battleScene.getStage().getPlayerY() + 300f,
                            BotrGSpriteArchive.getSprite(7000),(int)(screenWidth * 0.2083f), (int)(screenWidth * 0.2083f)),
                    new BotrGEffectTile(battleScene.getStage().getPlayerX() + screenWidth * 0.00347f, battleScene.getStage().getPlayerY() + 296f,
                            BotrGSpriteArchive.getSprite(7001),(int)(screenWidth * 0.1611f), (int)(screenWidth * 0.1611f))
            });
        }
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {
        if(frameCnt == ANIM_LENGTH) endAnimation(battleScene);

        if(playerIsAttacking){
            if(animActive && frameCnt >= 0){
                if(frameCnt <= 6 && frameCnt > 0){
                    battleScene.getAttackingChar().setPos(
                            battleScene.getAttackingChar().getXPos() - MOVEDISTANCE_X,
                            battleScene.getAttackingChar().getYPos() - MOVEDISTANCE_Y
                    );
                }
                if(frameCnt <= 10 && frameCnt > 6){
                    battleScene.getAttackingChar().setPos(
                            battleScene.getAttackingChar().getXPos() + MOVEDISTANCE_X * 2,
                            battleScene.getAttackingChar().getYPos() + MOVEDISTANCE_Y * 2
                    );
                }
                if(frameCnt == 11 || frameCnt == 12) {
                    battleScene.getAttackingChar().setPos(
                            battleScene.getAttackingChar().getXPos() - MOVEDISTANCE_X,
                            battleScene.getAttackingChar().getYPos() - MOVEDISTANCE_Y
                    );
                }
                if(frameCnt == 11) BotrGSceneBattle.effects[0].setAlpha(1.0f);
                if(frameCnt == 12) BotrGSceneBattle.effects[1].setAlpha(1.0f);
                if(frameCnt == 16) BotrGSceneBattle.effects[0].setAlpha(0.0f);
                if(frameCnt == 17) BotrGSceneBattle.effects[1].setAlpha(0.0f);
                frameCnt++;
            }
        } else {
            if(animActive && frameCnt >= 0){
                if(frameCnt <= 6 && frameCnt > 0){
                    battleScene.getAttackingChar().setPos(
                            battleScene.getAttackingChar().getXPos() + MOVEDISTANCE_X,
                            battleScene.getAttackingChar().getYPos() + MOVEDISTANCE_Y
                    );
                }
                if(frameCnt <= 10 && frameCnt > 6){
                    battleScene.getAttackingChar().setPos(
                            battleScene.getAttackingChar().getXPos() - MOVEDISTANCE_X * 2,
                            battleScene.getAttackingChar().getYPos() - MOVEDISTANCE_Y * 2
                    );
                }
                if(frameCnt == 11 || frameCnt == 12) {
                    battleScene.getAttackingChar().setPos(
                            battleScene.getAttackingChar().getXPos() + MOVEDISTANCE_X,
                            battleScene.getAttackingChar().getYPos() + MOVEDISTANCE_Y
                    );
                }
                if(frameCnt == 11) BotrGSceneBattle.effects[0].setAlpha(1.0f);
                if(frameCnt == 12) BotrGSceneBattle.effects[1].setAlpha(1.0f);
                if(frameCnt == 16) BotrGSceneBattle.effects[0].setAlpha(0.0f);
                if(frameCnt == 17) BotrGSceneBattle.effects[1].setAlpha(0.0f);
                frameCnt++;
            }
        }

        if(animActive && frameCnt < 0) frameCnt++;
    }

    @Override
    public void endAnimation(BotrGSceneBattle battleScene) {
        battleScene.getAttackingChar().resetPos(battleScene.getStage());
        battleScene.getDefendingChar().resetPos(battleScene.getStage());
        battleScene.resetEffects();
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
