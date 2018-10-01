package com.yourmom.events;

import com.yourmom.animation.AnimText;
import com.yourmom.animation.AnimTextArrow;
import com.yourmom.animation.BotrGAnimation;
import com.yourmom.animation.attacks.AnimHit;
import com.yourmom.botrgchar.Attack;
import com.yourmom.botrgchar.AttackAnimationArchive;
import com.yourmom.botrgchar.BotrGState;
import com.yourmom.botrgchar.Element;
import com.yourmom.screenassets.BotrGDialogue;
import com.yourmom.screenassets.BotrGSceneBattle;
import com.yourmom.screenassets.BotrGTextConvertor;

/**
 * Created by Ben on 23.07.2018.
 */

public class BotrGEventAttack implements BotrGEvent {

    private enum eventState {ATTACK, CALL_ATTACK, MISSED, ATTACK_SPLASH, DODGED, EVENT_END,
        INFLICT_DMG, INFLICT_STATE, INFLICT_STATE_TEXT, INFLICT_SELF_STATE, INFLICT_SELF_STATE_TEXT}
    private AnimText animText = new AnimText("");
    private AnimTextArrow arrow = new AnimTextArrow();
    private eventState currentEventState;
    private boolean eventStateChanging;
    private BotrGDialogue dialogue;


    private int attackSlot;
    private BotrGAnimation animation;
    private boolean attackHit = true;
    private boolean dodged = false;
    private boolean doesDmg = false;
    private int dmgDealt = 0;
    private boolean inflictsState = false;
    private boolean inflictsSelfState = false;
    private int inflictionStateIndex = 0;
    private boolean animActive = false;

    public BotrGEventAttack(int attackSlot){
        this.attackSlot = attackSlot;
    }


    @Override
    public void startEvent(BotrGSceneBattle battleScene) {
        System.out.println("EVENT_ATTACK: BotrGEventAttack started!");

        animActive = true;

        System.out.println("EVENT_ATTACK: Effective Attack Accuracy: " + (battleScene.getAttackingChar().getAccuracy() - battleScene.getAttackingChar().getAttacks()[attackSlot].getAccuracyMod()) * 100 + "%");
        System.out.println("EVENT_ATTACK: Effective Evasiveness: " + (battleScene.getDefendingChar().getEvasiveness() - battleScene.getAttackingChar().getAttacks()[attackSlot].getEvaDifficulty())* 100 + "%");

        //missed?
        if(battleScene.getAttackingChar().getAttacks()[attackSlot].hasAttribute(Attack.BotrGAttackAttribute.ACCURACY_MOD)
                && Math.random() > battleScene.getAttackingChar().getAccuracy() -
                    battleScene.getAttackingChar().getAttacks()[attackSlot].getAccuracyMod()){
            attackHit = false;
            System.out.println("EVENT_ATTACK: MISSED!");
        }

        //dodged?
        if(battleScene.getAttackingChar().getAttacks()[attackSlot].hasAttribute(Attack.BotrGAttackAttribute.EVA_DIFFICULTY)
                && Math.random() <= battleScene.getDefendingChar().getEvasiveness() -
                    battleScene.getAttackingChar().getAttacks()[attackSlot].getEvaDifficulty()){
            dodged = true;
            System.out.println("EVENT_ATTACK: DODGED!");
        }

        //dmg?
        if(battleScene.getAttackingChar().getAttacks()[attackSlot].hasAttribute(Attack.BotrGAttackAttribute.DMG))
            doesDmg = true;

        //inflict state?
        if(battleScene.getAttackingChar().getAttacks()[attackSlot].hasAttribute(Attack.BotrGAttackAttribute.INFLICT_STATE)) {
            inflictsState = true;
            for(int i = 0; i < battleScene.getAttackingChar().getAttacks()[attackSlot].getAttributes().length; i++) {
                if(battleScene.getAttackingChar().getAttacks()[attackSlot].getAttributes()[i] == Attack.BotrGAttackAttribute.INFLICT_STATE){
                    inflictionStateIndex = i;
                    break;
                }
            }
        }

        //inflict self state?
        if(battleScene.getAttackingChar().getAttacks()[attackSlot].hasAttribute(Attack.BotrGAttackAttribute.INFLICT_STATE_SELF))
            inflictsSelfState = true;

        //dmg mod:
        if(attackHit && !dodged && doesDmg){
            System.out.println("EVENT_ATTACK: ATTACK HIT!");
            float dmgMod = 1.0f;
            for(int i = 0; i < battleScene.getAttackingChar().getElements().length; i++){
                for(int j = 0; j < battleScene.getDefendingChar().getElements().length; j++){
                    dmgMod += Element.getDmgMod(
                            battleScene.getAttackingChar().getElements()[i].getType(),
                            battleScene.getAttackingChar().getElements()[i].getLvl(),
                            battleScene.getDefendingChar().getElements()[j].getType()
                    );
                    System.out.println("EVENT_ATTACK: Calc New DmgMod:");
                    System.out.println("EVENT_ATTACK:  AttEle: " + battleScene.getAttackingChar().getElements()[i].getType() +
                            " AttLvl: " + battleScene.getAttackingChar().getElements()[i].getLvl() +
                            " DefEle: " + battleScene.getDefendingChar().getElements()[j].getType());
                    System.out.println("EVENT_ATTACK:  New DmgMod: " + dmgMod);
                }
            }

            //dmg:
            dmgDealt = (int)(battleScene.getAttackingChar().getAttacks()[attackSlot].getDmg() * dmgMod);
            if(dmgDealt <= 0) {
                dmgDealt = 10;
            }

            System.out.println("EVENT_ATTACK: DMG DEALT: " + dmgDealt);
        }

        eventStateChanging = false;

        if(battleScene.getAttackingChar().getAttacks()[attackSlot].getAnnouncementAltText().equals("")) {
            dialogue = new BotrGDialogue(BotrGTextConvertor.replaceTags(
                    "<ATTNAME> used <ATTACKNAME>!",
                    battleScene,
                    battleScene.getAttackingChar().getAttacks()[attackSlot]
            ));
        } else
            dialogue = new BotrGDialogue(BotrGTextConvertor.replaceTags(
                battleScene.getAttackingChar().getAttacks()[attackSlot].getAnnouncementAltText(),
                battleScene,
                battleScene.getAttackingChar().getAttacks()[attackSlot]
        ));

        animation = AttackAnimationArchive.getAttackAnimation(
                battleScene.getAttackingChar().getAttacks()[attackSlot].getAnimationId()
        );

        currentEventState = eventState.CALL_ATTACK;
        dialogue.startDialogue();
    }

    @Override
    public void update(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        dialogue.update(battleScene);

        if(currentEventState == eventState.EVENT_END){
            if(eventStateChanging){
                eventStateChanging = false;
                endEvent(battleScene, eventCoor);
            }
        }

        if(currentEventState == eventState.CALL_ATTACK){
            if(dialogue.isOver()){
                eventStateChanging = true;
                currentEventState = getNextEventState(eventState.CALL_ATTACK, battleScene);
            }
        }

        if(currentEventState == eventState.MISSED){
            if(eventStateChanging){
                eventStateChanging = false;
                dialogue = new BotrGDialogue(BotrGTextConvertor.replaceTags("<ATTNAME> missed...", battleScene));
                dialogue.startDialogue();
            }
            if(dialogue.isOver()){
                eventStateChanging = true;
                currentEventState = getNextEventState(eventState.MISSED, battleScene);
            }
        }

        if(currentEventState == eventState.DODGED){
            if(eventStateChanging){
                eventStateChanging = false;
                dialogue = new BotrGDialogue(BotrGTextConvertor.replaceTags("<DEFNAME> dodged the attack!", battleScene));
                dialogue.startDialogue();
            }
            if(dialogue.isOver()){
                eventStateChanging = true;
                currentEventState = getNextEventState(eventState.DODGED, battleScene);
            }
        }

        if(currentEventState == eventState.ATTACK_SPLASH){
            if(eventStateChanging){
                eventStateChanging = false;
                dialogue = new BotrGDialogue(BotrGTextConvertor.replaceTags(
                        battleScene.getAttackingChar().getAttacks()[attackSlot].getSplash(),
                        battleScene));
                dialogue.startDialogue();

                System.out.println("EVENT_ATTACK: SPLASH");
            }
            if(dialogue.isOver()){
                eventStateChanging = true;
                currentEventState = getNextEventState(eventState.ATTACK_SPLASH, battleScene);
            }
        }

        if(currentEventState == eventState.ATTACK){
            if(eventStateChanging){
                eventStateChanging = false;

                if(!battleScene.getAttackingChar().getAttacks()[attackSlot].getMidAttackText().equals(""))
                    animText = new AnimText( BotrGTextConvertor.replaceTags(battleScene.getAttackingChar().getAttacks()[attackSlot].getMidAttackText(), battleScene));
                else animText = new AnimText( BotrGTextConvertor.replaceTags("<ATTNAME> attacks!", battleScene));

                animText.startAnimation(battleScene);
                animText.endAnimation(battleScene);

                animation.setDelay(30);
                animation.startAnimation(battleScene);
            }
            if(!animation.isActive()){
                eventStateChanging = true;
                currentEventState = getNextEventState(eventState.ATTACK, battleScene);
            }
        }

        if(currentEventState == eventState.INFLICT_DMG){
            if(eventStateChanging){
                eventStateChanging = false;
                animation = new AnimHit(battleScene, dmgDealt, 0, battleScene.getDefendingChar().checkIsPlayer());
                animation.startAnimation(battleScene);

                System.out.println("EVENT_ATTACK: Damage to: " + battleScene.getDefendingChar().getName());
                battleScene.getDefendingChar().reduceHp(dmgDealt);
            }
            if(!animation.isActive()){
                eventStateChanging = true;
                currentEventState = getNextEventState(eventState.INFLICT_DMG, battleScene);
            }
        }

        //TODO: make an existing state not receivable
        if(currentEventState == eventState.INFLICT_STATE){
            if(eventStateChanging){
                eventStateChanging = false;
                //set & start animation
                animation = BotrGState.getStateInflictAnimation(battleScene,
                        battleScene.getAttackingChar().getAttacks()[attackSlot].getAttributeState(Attack.BotrGAttackAttribute.INFLICT_STATE),
                        battleScene.getAttackingChar().checkIsPlayer());
                animation.startAnimation(battleScene);

                //add state
                float[] stateValues = new float[battleScene.getAttackingChar().getAttacks()[attackSlot].getAttributeValues(Attack.BotrGAttackAttribute.INFLICT_STATE).length - 1];
                System.arraycopy(battleScene.getAttackingChar().getAttacks()[attackSlot].getAttributeValues(Attack.BotrGAttackAttribute.INFLICT_STATE),
                        1, stateValues, 0, stateValues.length);

                battleScene.getDefendingChar().addState(battleScene.getAttackingChar().getAttacks()[attackSlot].getAttributeState(Attack.BotrGAttackAttribute.INFLICT_STATE),
                        stateValues);
            }
            if(!animation.isActive()){
                eventStateChanging = true;
                currentEventState = getNextEventState(eventState.INFLICT_STATE, battleScene);
            }
        }

        if(currentEventState == eventState.INFLICT_STATE_TEXT){
            if(eventStateChanging){
                eventStateChanging = false;
                dialogue = new BotrGDialogue(
                        BotrGTextConvertor.replaceTags(
                            BotrGState.getStateInflictionText(
                                    battleScene.getAttackingChar().getAttacks()[attackSlot].getAttributeState(Attack.BotrGAttackAttribute.INFLICT_STATE),
                                    false), battleScene));

                dialogue.startDialogue();
            }
            if(dialogue.isOver()){
                eventStateChanging = true;
                currentEventState = getNextEventState(eventState.INFLICT_STATE_TEXT, battleScene);
            }
        }

        //TODO: make INFLICT_SELF_STATE work
        if(currentEventState == eventState.INFLICT_SELF_STATE){
            if(eventStateChanging){
                eventStateChanging = false;
                animation = BotrGState.getStateInflictAnimation(battleScene, BotrGState.getStateFromId(0), battleScene.getAttackingChar().checkIsPlayer());
                animation.startAnimation(battleScene);
            }
            if(!animation.isActive()){
                eventStateChanging = true;
                currentEventState = getNextEventState(eventState.INFLICT_STATE, battleScene);
            }
        }
    }

    @Override
    public void updateAnimation(BotrGSceneBattle battleScene) {
        if(animActive){
            dialogue.updateAnimation(battleScene);
            animText.updateAnimation(battleScene);
            //arrow.updateAnimation(battleScene);
            animation.updateAnimation(battleScene);
        }
    }

    @Override
    public void endEvent(BotrGSceneBattle battleScene, BotrGEventCoordinator eventCoor) {
        animActive = false;

        if(animText.isActive()) animText.endAnimation(battleScene);
        if(arrow.isActive()) arrow.endAnimation(battleScene);
        animation.endAnimation(battleScene);
        battleScene.getGui().resetLines();

        eventCoor.setNextEvent(new BotrGEventStateDmg());

        System.out.println("EVENT_ATTACK: End");
        eventCoor.nextEvent();
    }

    private eventState getNextEventState(eventState currentEventState, BotrGSceneBattle battleScene){
        switch(currentEventState){
            default: return eventState.CALL_ATTACK;
            case CALL_ATTACK:
                if(!attackHit) return eventState.MISSED;
                else if(dodged) return eventState.DODGED;
                else if(!battleScene.getAttackingChar().getAttacks()[attackSlot].getSplash().equals(""))
                    return eventState.ATTACK_SPLASH;
                else return eventState.ATTACK;
            case ATTACK_SPLASH:
                return eventState.ATTACK;
            case ATTACK:
                if(doesDmg) return eventState.INFLICT_DMG;
                else if(inflictsSelfState) return eventState.INFLICT_SELF_STATE;
                else if(inflictsState) return eventState.INFLICT_STATE;
            case INFLICT_DMG:
                if(inflictsSelfState) return eventState.INFLICT_SELF_STATE;
                else if(inflictsState) return eventState.INFLICT_STATE;
                else return eventState.EVENT_END;
            case INFLICT_STATE:
                return eventState.INFLICT_STATE_TEXT;
            case INFLICT_SELF_STATE:
                return eventState.INFLICT_SELF_STATE_TEXT;
            case INFLICT_SELF_STATE_TEXT:
                if(inflictsSelfState) return eventState.INFLICT_STATE;
                else return eventState.EVENT_END;
            case INFLICT_STATE_TEXT:
                return eventState.EVENT_END;
            case MISSED:
                return eventState.EVENT_END;
            case DODGED:
                return eventState.EVENT_END;
        }
    }
}
