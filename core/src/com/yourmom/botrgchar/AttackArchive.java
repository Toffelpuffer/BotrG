package com.yourmom.botrgchar;

/**
 * Created by Ben on 11.07.2018.
 */

public final class AttackArchive {

    private AttackArchive(){}

    public static Attack getAttack(int attackId){
        switch(attackId){
            case 0: return new Attack("", "", "", "",0,
                    new Attack.BotrGAttackAttribute[] {}, new float[][] {},
                    EleType.NORMAL);
            case 1: return new Attack("Punch",  "", "", "", 1,
                    new Attack.BotrGAttackAttribute[] {Attack.BotrGAttackAttribute.DMG, Attack.BotrGAttackAttribute.ACCURACY_MOD},
                    new float[][] {new float[]{300.0f}, new float[]{0.1f}},
                    EleType.NORMAL);
            case 2: return new Attack("Throw Lego",  "", "", "", 2,
                    new Attack.BotrGAttackAttribute[] {Attack.BotrGAttackAttribute.ACCURACY_MOD, Attack.BotrGAttackAttribute.INFLICT_STATE},
                    new float[][] {new float[]{0.1f}, new float[]{(float)BotrGState.getIdFromState(BotrGState.LEGO), 1.0f, 0.5f, 0.0f, 0.0f}},
                    EleType.NORMAL);


            default: return new Attack("ATTACK_NOT_FOUND", "", "", "",0,
                    Attack.BotrGAttackAttribute.ACCURACY_MOD, new float[] {1.0f},
                    EleType.NORMAL);
        }
    }

}
