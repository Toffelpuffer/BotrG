package com.yourmom.events;

import com.yourmom.animation.BotrGAnimation;
import com.yourmom.botrgchar.BotrGState;
import com.yourmom.screenassets.BotrGDialogue;
import com.yourmom.screenassets.BotrGSceneBattle;
import com.yourmom.screenassets.BotrGTextConvertor;

/**
 * Created by Ben on 8/24/2018.
 */

public class BotrGEventStateDmg implements BotrGEvent {

    private BotrGDialogue dialogue;
    private BotrGAnimation animation;
    private int nextState;
    private boolean lastState;
    private boolean playerHasStates;
    private int displayState;

    public BotrGEventStateDmg(){
        nextState = 0;
    }

    @Override
    public void startEvent(BotrGSceneBattle battleScene) {
        System.out.println("EVENT_STATE_DMG: BotrGEventStateDmg started!");

        playerHasStates = charGetsStateDmg(battleScene);

        displayState = 0;

        if(playerHasStates) nextState(battleScene);
    }

    @Override
    public void update(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        if(playerHasStates){
            if(dialogue.isOver() && !animation.isActive()){
                if(lastState){
                    //endEvent(battleScene, eventCoor);
                    playerHasStates = false;
                } else {
                    displayState = 0;
                }
            } else if(displayState == 1 && !animation.isActive()){
                displayState = 2;
                dialogue.startDialogue();

            } else if(displayState == 0){
                displayState = 1;
                nextState(battleScene);
                animation.startAnimation(battleScene);
            }
            dialogue.update(battleScene);
        }
        else endEvent(battleScene, eventCoor);
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {
        if(playerHasStates){
            dialogue.updateAnimation(battleScene);
            animation.updateAnimation(battleScene);
        }

    }

    @Override
    public void endEvent(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        battleScene.getAttackingChar().updateChar();

        battleScene.getGui().resetLines();

        if(battleScene.getAttackingChar().checkIsPlayer()){
            battleScene.setPlayerIsAttacking(false);
            eventCoor.setNextEvent(new BotrGEventEnemyChoice());
        } else {
            battleScene.setPlayerIsAttacking(true);
            eventCoor.setNextEvent(new BotrGEventPlayerChoice());
        }

        System.out.println("EVENT_STATE_DMG: End");
        eventCoor.nextEvent();
    }

    /**
     * Looks for the next state that does dmg and sets the dialogue and animation,
     * sets @param lastState to true if the next damaging state is the last one and
     * reduces the chars Hp accordingly.
     *
     * @param battleScene the BotrGBattleScene in which the fight is happening
     */
    private void nextState(BotrGSceneBattle battleScene){
        for(int i = nextState; i < battleScene.getAttackingChar().getStates().length; i++){
            if(BotrGState.doesStateDmg(battleScene.getAttackingChar(), nextState)){
                System.out.println("EVENT_STATE_DMG: Damaging State found: '"+
                        battleScene.getAttackingChar().getStates()[nextState] +"'");

                dialogue = new BotrGDialogue(BotrGTextConvertor.replaceTags(
                        BotrGState.getStateDmgText(battleScene.getAttackingChar().getStates()[nextState]), battleScene)
                        );
                animation = BotrGState.getStateDmgAnimation( battleScene,
                        battleScene.getAttackingChar().getStates()[nextState],
                        (int)battleScene.getAttackingChar().getStateValues()[nextState][1],
                        battleScene.getAttackingChar().checkIsPlayer() );
                animation.setDelay(15);
                battleScene.getAttackingChar().reduceHp(
                        (int)battleScene.getAttackingChar().getStateValues()[nextState][1]);

                nextState++;
                break;
            } else {
                System.out.println("EVENT_STATE_DMG: State '" + battleScene.getAttackingChar().getStates()[nextState] + "' skipped, it does no damage!");
                if(nextState < battleScene.getAttackingChar().getStates().length) nextState++;
            }
        }
        System.out.println(battleScene.getAttackingChar().getStates().length);
        if(nextState == battleScene.getAttackingChar().getStates().length){
            lastState = true;
            System.out.println("EVENT_STATE_DMG: last state...");
        }
    }

    /**
     * @return true if the attacking char has a state that damages him
     */
    private boolean charGetsStateDmg(BotrGSceneBattle battleScene){
        for(BotrGState state: battleScene.getAttackingChar().getStates())
            if(BotrGState.doesStateDmg(state)) return true;
        return false;
    }
}
