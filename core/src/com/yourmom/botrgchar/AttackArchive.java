package com.yourmom.botrgchar;

import com.yourmom.botrgchar.AttackAttribute.AttAttributeType.*;

/**
 * Created by Ben on 11.07.2018.
 */

public final class AttackArchive {

    private AttackArchive(){}

    public static Attack getAttack(int attackId){
        switch(attackId){
            //TODO: create new attacks...
            case 0: return new Attack("", "", "", "",0,
                    new AttackAttribute[] {},
                    EleType.NORMAL);
            case 1 : return new Attack("Punch", "", "", "", 1,
                    new AttackAttribute[]{
                        new AttackAttribute(AttackAttribute.AttAttributeType.DMG, new float[]{300.0f}),
                        new AttackAttribute(AttackAttribute.AttAttributeType.ACCURACY_MOD, new float[]{0.1f})},
                    EleType.NORMAL);
            case 2: return new Attack("Throw Lego",  "", "", "", 2,
                    new AttackAttribute[] {
                        new AttackAttribute(AttackAttribute.AttAttributeType.ACCURACY_MOD, new float[]{0.1f}),
                        new AttackAttribute(AttackAttribute.AttAttributeType.INFLICT_STATE, new float[]{},
                                new BotrGState(BotrGStateType.LEGO, new float[]{1.0f, 0.5f, 0.0f, 0.0f}))
                    },
                    EleType.NORMAL);


            default: return new Attack("ATTACK_NOT_FOUND", "", "", "",0,
                    new AttackAttribute(AttackAttribute.AttAttributeType.ACCURACY_MOD, new float[]{1.0f}),
                    EleType.NORMAL);
        }
    }

}
