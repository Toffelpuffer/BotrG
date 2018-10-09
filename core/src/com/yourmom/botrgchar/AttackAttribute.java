package com.yourmom.botrgchar;

/**
 * Created by BenK on 09.10.2018.
 */

public class AttackAttribute {
    public enum AttAttributeType {
        NONE, DMG, HEAL, ABSORB, INFLICT_STATE, INFLICT_STATE_SELF, ACCURACY_MOD, EVA_DIFFICULTY
    }

    private AttAttributeType type;
    private float[] values;
    private BotrGState state;

    public AttackAttribute(AttAttributeType type, float[] values, BotrGState state){
        this.type = type;
        this.values = values;
        this.state = state;
    }

    public AttackAttribute(AttAttributeType type, float[] values){
        this.type = type;
        this.values = values;
        this.state = new BotrGState(BotrGStateType.NONE, new float[0]);
    }

    public AttAttributeType getType() {
        return type;
    }

    public float[] getValues() {
        return values;
    }

    public BotrGState getState() {
        return state;
    }
}
