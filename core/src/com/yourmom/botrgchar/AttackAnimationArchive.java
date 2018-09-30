package com.yourmom.botrgchar;


import com.yourmom.animation.BotrGAnimation;
import com.yourmom.animation.attacks.AnimNormalPunch;
import com.yourmom.screenassets.BotrGSceneBattle;

/**
 * Created by Ben on 24.07.2018.
 */

public class AttackAnimationArchive {
    private AttackAnimationArchive(){}

    public static BotrGAnimation getAttackAnimation(int animationId){
        switch(animationId){
            case 1: return new AnimNormalPunch();


            default: return new BotrGAnimation() {
                boolean active = false;
                @Override
                public void startAnimation(BotrGSceneBattle battleScene) {active = true;}

                @Override
                public void updateAnimation(BotrGSceneBattle battleScene) {active = false;}

                @Override
                public void endAnimation(BotrGSceneBattle battleScene) {}

                @Override
                public boolean isActive() {return active;}

                @Override
                public void setDelay(int delayedFrames) {}
            };
        }
    }
}
