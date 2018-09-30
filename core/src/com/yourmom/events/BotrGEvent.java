package com.yourmom.events;

import com.yourmom.screenassets.BotrGSceneBattle;

/**Interface for events that happen in the game.
 * Created by Ben on 13.07.2018.
 */


interface BotrGEvent {
    void startEvent(BotrGSceneBattle battleScene);
    void update(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor);
    void updateAnimation(BotrGSceneBattle battleScene);
    void endEvent(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor);
}
