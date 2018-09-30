package com.yourmom.events;

import com.yourmom.botrgchar.Attack;
import com.yourmom.screenassets.BotrGSceneBattle;

/**
 * Created by Ben on 30.07.2018.
 */

public class BotrGEventEnemyChoice implements BotrGEvent {
    private int availableAttacks = 0;
    private int nextAttack = 0;

    @Override
    public void startEvent(BotrGSceneBattle battleScene) {
        System.out.println("ENEMY ATTACK: BotrGEventEnemyChoice started!");

        for(Attack attack : battleScene.getEnemyChar().getAttacks()){
            if(!attack.getDisplayName().equals("")) availableAttacks++;
        }

        nextAttack = (int)(Math.random() * availableAttacks);
        System.out.println("ENEMY ATTACK: Enemy Attack Slot: " + nextAttack);
    }

    @Override
    public void update(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        endEvent(battleScene, eventCoor);
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {

    }

    @Override
    public void endEvent(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        battleScene.getGui().resetLines();

        eventCoor.setNextEvent(new BotrGEventStatusCheck(nextAttack));
        eventCoor.nextEvent();
    }
}
