package com.yourmom.botrgchar;

import com.yourmom.screenassets.BotrGSprite;
import com.yourmom.screenassets.BotrGSpriteArchive;

/**
 * Created by Ben on 10.07.2018.
 */

public class BodyType {
    private String displayName = "NOT_SET";
    private String shortDisplayName = "NOT_SET";
    private BotrGSprite frontSprite;
    private BotrGSprite backSprite;
    private int baseHealth;
    private Element[] charElements;
    private Attack[] signatureAttacks;


    public BodyType(String displayName, String shortDisplayName, int id, int backSpriteId, int baseHealth, Element[] elements) {
        this.displayName = displayName;
        this.shortDisplayName = shortDisplayName;
        this.frontSprite = BotrGSpriteArchive.getSprite(id);
        this.backSprite = BotrGSpriteArchive.getSprite(backSpriteId);
        this.baseHealth = baseHealth;
        charElements = elements;
        this.signatureAttacks = new Attack[] {AttackArchive.getAttack(id)};
    }

    public String getDisplayName() {return displayName;}
    public String getShortDisplayName() {return shortDisplayName;}
    public BotrGSprite getFrontSprite() {return frontSprite;}
    public BotrGSprite getBackSprite() {return backSprite;}
    public int getBaseHealth() {return baseHealth;}
    public Attack[] getSignatureAttack() {return signatureAttacks;}
    public Element[] getCharElements() {return charElements;}

    public void setSprite(BotrGSprite newSprite) {this.frontSprite = newSprite;}
}