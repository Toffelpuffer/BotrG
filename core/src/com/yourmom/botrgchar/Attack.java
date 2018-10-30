package com.yourmom.botrgchar;

import static com.yourmom.botrgchar.AttackAttribute.AttAttributeType.*;

/**
 * Created by Ben on 11.07.2018.
 */

public class Attack {
    //TODO: Make BotrGAttackAttribute its own class.

    private String displayName;
    private String splash;
    private String announcementAltText;
    private String midAttackText;
    private EleType[] eleTypes;
    private AttackAttribute[] attributes;
    private int animationId;


    public Attack(String displayName, String splash, String announcement, String midAttackText, int animationId,
                  AttackAttribute[] attributes,
                  EleType[] eleTypes){

        setup(displayName, splash, announcement, midAttackText, animationId, attributes,
                eleTypes);
    }

    public Attack(String displayName, String splash, String announcement, String midAttackText, int animationId,
                  AttackAttribute[] attributes,
                  EleType eleType){

        setup(displayName, splash, announcement, midAttackText, animationId, attributes,
                new EleType[]{eleType});
    }

    public Attack(String displayName, String splash, String announcement, String midAttackText, int animationId,
                  AttackAttribute attribute,
                  EleType eleType){

        setup(displayName, splash, announcement, midAttackText, animationId,
                new AttackAttribute[]{attribute},
                new EleType[]{eleType});
    }

    private void setup(String displayName, String splash, String announcement, String midAttackText, int animationId,
                       AttackAttribute[] attributes,
                       EleType[] eleTypes){

        this.displayName = displayName;
        this.splash = splash;
        announcementAltText = announcement;
        this.midAttackText = midAttackText;
        this.animationId = animationId;
        this.attributes = attributes;
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
        return getAttribute(DMG).getValues()[0];
    }
    public float getAccuracyMod(){
        return getAttribute(ACCURACY_MOD).getValues()[0];
    }
    public float getEvaDifficulty(){
        if(hasAttribute(EVA_DIFFICULTY))
            return getAttribute(EVA_DIFFICULTY).getValues()[0];
        else return 0.0f;
    }

    public EleType[] getEleTypes() {return eleTypes;}
    public AttackAttribute[] getAttributes(){return attributes;}
    public int getAnimationId() {return animationId;}


    public boolean hasAttribute(AttackAttribute.AttAttributeType attributeType){
        for(AttackAttribute currentAttr: attributes){
            if(currentAttr.getType() == attributeType) return true;
        }
        return false;
    }

    public AttackAttribute getAttribute(AttackAttribute.AttAttributeType AttributeType){
        for(AttackAttribute attAttribute : attributes){
            if(attAttribute.getType() == AttributeType) return attAttribute;
        }
        return new AttackAttribute(NONE, new float[]{});
    }
}
