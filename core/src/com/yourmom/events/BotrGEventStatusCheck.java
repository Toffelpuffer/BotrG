package com.yourmom.events;

import com.yourmom.botrgchar.BotrGState;
import com.yourmom.screenassets.BotrGChar;
import com.yourmom.screenassets.BotrGDialogue;
import com.yourmom.screenassets.BotrGSceneBattle;
import com.yourmom.screenassets.BotrGTextConvertor;

import static com.yourmom.botrgchar.BotrGState.getStateActiveText;
import static com.yourmom.botrgchar.BotrGState.getStateOverText;
import static com.yourmom.botrgchar.BotrGState.interrupts;

/**
 * Created by Ben on 23.07.2018.
 */

public class BotrGEventStatusCheck implements BotrGEvent {

    private int nextAttack;
    private BotrGDialogue[] dialogues;
    private int activeDialogue;
    private boolean playerHasStates;
    private boolean attackInterrupted;

    public BotrGEventStatusCheck(int nextAttack){
        this.nextAttack = nextAttack;

        attackInterrupted = false;

        playerHasStates = false;
        dialogues = new BotrGDialogue[0];

        activeDialogue = 0;
    }

    @Override
    public void startEvent(BotrGSceneBattle battleScene) {
        System.out.println("EVENT_STATUS_CHECK: BotrGEventStatusCheck started!");

        setStatusDialogues(battleScene);

        BotrGState.removeExpiredStates(battleScene.getAttackingChar());

        setInterruption(battleScene);

        if(dialogues.length != 0) playerHasStates = true;
        if(playerHasStates) dialogues[0].startDialogue();



        System.out.println("EVENT_STATUS_CHECK: Dialogues: " + dialogues.length);
    }

    @Override
    public void update(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        if(playerHasStates){
            dialogues[activeDialogue].update(battleScene);

            if(dialogues[activeDialogue].isOver()){

                if(activeDialogue + 1 != dialogues.length) {
                    activeDialogue++;
                    dialogues[activeDialogue].startDialogue();
                } else {
                    playerHasStates = false;
                    endEvent(battleScene, eventCoor);
                }
            }
        }
        else endEvent(battleScene, eventCoor);
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {
        if(playerHasStates){
            dialogues[activeDialogue].updateAnimation(battleScene);
        }
    }

    @Override
    public void endEvent(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        battleScene.getGui().resetLines();

        if(!attackInterrupted) eventCoor.setNextEvent(new BotrGEventAttack(nextAttack));
        else eventCoor.setNextEvent(new BotrGEventStateDmg());

        eventCoor.nextEvent();
    }


    private void setStatusDialogues(BotrGSceneBattle battleScene){
        for(int i = 0; i < battleScene.getAttackingChar().getStates().length; i++){
            if(BotrGState.hasStatusCheckMsg(battleScene.getAttackingChar().getStates()[i])){
                if(battleScene.getAttackingChar().getStateValues()[i][0] > 0)
                    addDialogue(new BotrGDialogue(
                            BotrGTextConvertor.replaceTags(
                                    getStateActiveText(battleScene.getAttackingChar().getStates()[i]), battleScene)
                            )
                    );
                else if(battleScene.getAttackingChar().getStateValues()[i][0] == 0){
                    addDialogue(new BotrGDialogue(
                            BotrGTextConvertor.replaceTags(
                                    getStateOverText(battleScene.getAttackingChar().getStates()[i]), battleScene)
                            )
                    );
                }
            }
        }
    }

    private void addDialogue(BotrGDialogue newDialogue){
        BotrGDialogue[] newDialogues = new BotrGDialogue[dialogues.length + 1];
        System.arraycopy(dialogues, 0, newDialogues, 0,  dialogues.length);
        newDialogues[dialogues.length] = newDialogue;

        dialogues = newDialogues;
    }

    private void setInterruption(BotrGSceneBattle battleScene){
        setInterruption(battleScene, battleScene.getAttackingChar());
    }

    private void setInterruption(BotrGSceneBattle battleScene, BotrGChar character){
        int activatedInterruption = 0;
        int interruptions = 0;
        boolean[] potInterruption = new boolean[character.getStates().length];

        System.out.println("EVENT_STATUS_CHECK: checking interruptions...");

        for(int i= 0; i < character.getStates().length; i++)
            if(interrupts(character.getStates()[i])
                    && Math.random() < character.getStateValues()[i][1]){

                System.out.println("EVENT_STATUS_CHECK: activated interruption: " + character.getStates()[i]);
                attackInterrupted = true;
                interruptions++;
                potInterruption[i] = true;
            }

        System.out.println("EVENT_STATUS_CHECK: active interruption cnt: " + interruptions);

        if(interruptions > 0){
            int chosenInterruption = (int)(Math.random() * interruptions) + 1;
            System.out.print("EVENT_STATUS_CHECK: chosen interruption: " + chosenInterruption);

            int passedInterruptions = 0;
            for(int i = 0; i < character.getStates().length; i++){
                if(potInterruption[i]){
                    passedInterruptions++;
                    if(passedInterruptions == chosenInterruption){
                        System.out.println(" -> " + character.getStates()[i]);
                        activatedInterruption = i;
                        break;
                    }
                }
            }

            BotrGState.interrupted(character, character.getStates()[activatedInterruption]);

            addDialogue(new BotrGDialogue(
                    BotrGTextConvertor.replaceTags(
                            BotrGState.getInterruptionText(character.getStates()[activatedInterruption]),
                            battleScene
                    )
            ));
        } else System.out.println("EVENT_STATUS_CHECK: no interruptions activated!");
    }
}
