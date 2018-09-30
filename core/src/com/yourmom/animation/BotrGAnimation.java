package com.yourmom.animation;

import com.yourmom.screenassets.BotrGSceneBattle;

/**
 * Created by Ben on 18.07.2018.
 */

public abstract class BotrGAnimation {

    abstract public void startAnimation(BotrGSceneBattle battleScene);
    abstract public void updateAnimation(BotrGSceneBattle battleScene);
    abstract public void endAnimation(BotrGSceneBattle battleScene);
    abstract public boolean isActive();
    abstract public void setDelay(int delayedFrames);
}
