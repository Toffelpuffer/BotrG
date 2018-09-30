package com.yourmom.botrgchar;

/**
 * Created by Ben on 11.07.2018.
 */

public class Attack {
    public enum BotrGAttackAttribute {
        DMG, HEAL, ABSORB, INFLICT_STATE, INFLICT_STATE_SELF, ACCURACY_MOD, EVA_DIFFICULTY
    }
    private String displayName;
    private String splash;
    private String announcementAltText;
    private String midAttackText;
    private EleType[] eleTypes;
    private BotrGAttackAttribute[] attributes;
    private float[][] attrValues;
    private int animationId;


    public Attack(String displayName, String splash, String announcement, String midAttackText, int animationId,
                  BotrGAttackAttribute[] attributes, float[][] attrValues,
                  EleType[] eleTypes){

        setup(displayName, splash, announcement, midAttackText, animationId, attributes, attrValues,
                eleTypes);
    }

    public Attack(String displayName, String splash, String announcement, String midAttackText, int animationId,
                  BotrGAttackAttribute[] attributes, float[][] attrValues,
                  EleType eleType){

        setup(displayName, splash, announcement, midAttackText, animationId, attributes, attrValues,
                new EleType[]{eleType});
    }

    public Attack(String displayName, String splash, String announcement, String midAttackText, int animationId,
                  BotrGAttackAttribute attribute, float[] attrValue,
                  EleType eleType){

        setup(displayName, splash, announcement, midAttackText, animationId,
                new BotrGAttackAttribute[]{attribute}, new float[][]{attrValue},
                new EleType[]{eleType});
    }

    private void setup(String displayName, String splash, String announcement, String midAttackText,
                       int animationId, BotrGAttackAttribute[] attributes, float[][] attrValues,
                       EleType[] eleTypes){

        this.displayName = displayName;
        this.splash = splash;
        announcementAltText = announcement;
        this.midAttackText = midAttackText;
        this.animationId = animationId;
        this.attributes = attributes;
        this.attrValues = attrValues;
        this.eleTypes = eleTypes;
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getAnnouncementAltText() {
        return announcementAltText;
    }
    public String getMidAttackText() {
        return midAttackText;
    }
    public String getSplash() {
        return splash;
    }
    public float getDmg(){
        return getAttributeValues(BotrGAttackAttribute.DMG)[0];
    }
    public float getAccuracyMod(){
        return getAttributeValues(BotrGAttackAttribute.ACCURACY_MOD)[0];
    }
    public float getEvaDifficulty(){
        return getAttributeValues(BotrGAttackAttribute.EVA_DIFFICULTY)[0];
    }

    public EleType[] getEleTypes() {return eleTypes;}
    public BotrGAttackAttribute[] getAttributes(){return attributes;}
    public int getAnimationId() {return animationId;}


    public boolean hasAttribute(BotrGAttackAttribute attribute){
        for(BotrGAttackAttribute currentAttr: attributes){
            if(currentAttr == attribute) return true;
        }
        return false;
    }

    public float[] getAttributeValues(BotrGAttackAttribute attribute){
        for(int i = 0; i < attributes.length; i++)
            if(attributes[i] == attribute) return attrValues[i];
        return new float[]{0.0f};
    }

    public BotrGState getAttributeState(BotrGAttackAttribute attribute){
        for(int i = 0; i < attributes.length; i++){
            if(attributes[i] == attribute){
                return BotrGState.getStateFromId((int)getAttributeValues(attributes[i])[0]);
            }
        }
        return BotrGState.NONE;
    }
}
