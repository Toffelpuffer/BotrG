package com.yourmom.botrgchar;

import com.yourmom.animation.BotrGAnimation;
import com.yourmom.animation.attacks.AnimHit;
import com.yourmom.animation.states.AnimStatePoisonDmg;
import com.yourmom.animation.states.AnimStatePoisonInflict;
import com.yourmom.screenassets.BotrGChar;
import com.yourmom.screenassets.BotrGSceneBattle;

public class BotrGState{

    private BotrGStateType type;
    private float[] values;

    public BotrGState(BotrGStateType type, float[] values){
        this.type = type;
        this.values = values;
    }

        //TODO: State from enum into class and remove stateValues from char

        public BotrGStateType getType(){return type;}
        public float[] getValues(){return values;}

        //TODO: add remaining state messages and functionality

        public static String getStateDmgText(BotrGState state) {
            switch (state.getType()) {
                default:
                    return "<NO_MESSAGE_SET>";
                case POISON:
                    return "<ATTNAME> got damaged by Poison!";
                case LEGO:
                    return "<ATTNAME> got hurt while stepping on a Lego Brick!";
                case BLEED:
                    return "<ATTNAME> bleeds!";
            }
        }

        public static String getStateInflictionText(BotrGState state, boolean attackerGetsState){
            switch(state.getType()){
                default:
                    return "<NO_MESSAGE_SET>";
                case POISON:
                    if(attackerGetsState) return "<ATTNAME> got poisoned!";
                    else return "<DEFNAME> got poisoned!";
                case LEGO:
                    if(attackerGetsState) return "Lego Bricks lay around <ATTNAME>!";
                    else return "Lego Bricks lay around <DEFNAME>!";
            }
        }

        public static String getStateActiveText(BotrGState state) {
            switch (state.getType()) {
                default:
                    return "<NO_MESSAGE_SET>";
                case POISON:
                    return "<ATTNAME> is poisoned!";
                case LEGO:
                    return "<ATTNAME> walks over Lego Bricks.";
            }
        }

        public static String getStateOverText(BotrGState state) {
            switch (state.getType()) {
                default:
                    return "<NO_MESSAGE_SET>";
                case POISON:
                    return "<ATTNAME> is no longer poisoned!";
                case LEGO:
                    return "<ATTNAME> is not walking over Lego Bricks anymore!";
            }
        }

        public static String getInterruptionText(BotrGState state){
            switch(state.getType()){
                default:
                    return "<NO_MESSAGE_SET>";
                case LEGO:
                    return "<ATTNAME> stepped on a Lego Brick!";
            }
        }

        public static BotrGAnimation getStateDmgAnimation(
                BotrGSceneBattle battleScene, BotrGState state, int dmg, boolean harmedCharIsPlayer){
            switch(state.getType()){
                default: return new AnimHit(battleScene, dmg, 0, harmedCharIsPlayer);
                case POISON: return new AnimStatePoisonDmg(harmedCharIsPlayer);
            }
        }

        public static BotrGAnimation getStateInflictAnimation(
                BotrGSceneBattle battleScene, BotrGState state, boolean harmedCharIsPlayer) {
            switch(state.getType()){
                default: return AttackAnimationArchive.getAttackAnimation(0);
                case POISON: return new AnimStatePoisonInflict(/*battleScene, harmedCharIsPlayer*/);
            }
        }

        /*
        public static BotrGState getStateFromId(int id){
            switch(id){
                default: return NONE;
                case 1: return DIZZY;
                case 2: return LEGO;
                case 3: return BLEED;
                case 4: return SHOCK;
                case 5: return POISON;
                case 6: return IMPRISONED;
            }
        }

        public static int getIdFromState(BotrGState state){
            switch(state){
                default: return 0;
                case DIZZY: return 1;
                case LEGO: return 2;
                case BLEED: return 3;
                case SHOCK: return 4;
                case POISON: return 5;
                case IMPRISONED: return 6;
            }
        }*/

        public static boolean hasStatusCheckMsg(BotrGState state){
            switch(state.getType()){
                default: return false;
                case POISON: return true;
                case LEGO: return true;
            }
        }

        public static boolean doesStateDmg(BotrGState state){
            switch(state.getType()){
                default: return false;
                case POISON: return true;
                case BLEED: return true;
            }
        }
        public static boolean doesStateDmg(BotrGChar character, int stateIndex){
            return doesStateDmg(character.getStates()[stateIndex]);
        }

        public static boolean interrupts(BotrGState state){
            switch(state.getType()){
                default: return false;
                case LEGO: return true;
                case DIZZY: return true;
                case SHOCK: return true;
                case IMPRISONED: return true;
            }
        }

        public static void turnEnd(BotrGChar character){
            for(int i = 0; i < character.getStates().length; i++) {
                switch (character.getStates()[i].getType()) {
                    default:
                        character.getStates()[i].getValues()[0]--;
                        break;
                    case LEGO:
                        if(character.getStates()[i].getValues()[3] == 1.0f) character.getStates()[i].getValues()[0] = 0.0f;
                        break;
                }
            }
        }

        public static void interrupted(BotrGChar character, BotrGState interruptingState){
            switch(interruptingState.getType()){
                default: break;
                case LEGO:
                    for(int i = 0; i < character.getStates().length; i++)
                        if(character.getStates()[i].getType() == BotrGStateType.LEGO) character.getStates()[i].getValues()[3] = 1.0f;
            }
        }

        public static void removeExpiredStates(BotrGChar character){
            for(int i = 0; i < character.getStates().length; i++){
                switch(character.getStates()[i].getType()){
                    default:
                        if(character.getStates()[i].getValues()[0] <= 0){
                            character.removeState(character.getStates()[i]);
                            i--;
                        }
                        break;
                }
            }
        }
    }