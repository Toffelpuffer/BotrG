package com.yourmom.screenassets;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.yourmom.botrgchar.Attack;
import com.yourmom.botrgchar.AttackArchive;
import com.yourmom.botrgchar.BodyType;
import com.yourmom.botrgchar.BotrGModifier;
import com.yourmom.botrgchar.BotrGState;
import com.yourmom.botrgchar.BotrGStatus;
import com.yourmom.botrgchar.Element;

/**
 * Created by Ben on 10.07.2018.
 */

public class BotrGChar{
    private BotrGTile charTile;
    private BodyType baseBody;
    private BotrGStatus status;
    private String charName;
    private int hp;
    private int baseHp;
    private Element[] elements;
    private float evasiveness;
    private float baseEvasiveness;
    private float accuracy;
    private float baseAccuracy;
    private float dmgPerc;
    private float baseDmgPerc;
    private Attack[] attacks;
    private boolean isPlayer;


    /**
     *
     * @param x x position
     * @param y y position
     * @param bodyType BodyType of this character
     */
    public BotrGChar(float x, float y, BodyType bodyType, int sizeWidth, int sizeHeight,
                     boolean isPlayer, BotrGModifier[] mods, float[] modValues){
        this.isPlayer = isPlayer;

        baseBody = bodyType;
        status = new BotrGStatus();
        status.setModifiers(mods, modValues);
        charName = status.generateCharName(bodyType.getDisplayName(), bodyType.getShortDisplayName());

        if(!isPlayer){charTile = new BotrGTile(x, y, baseBody.getFrontSprite(), sizeWidth, sizeHeight, CenterType.WIDTH_CENTERED);}
        else {charTile = new BotrGTile(x, y, baseBody.getBackSprite(), sizeWidth, sizeHeight, CenterType.WIDTH_CENTERED);}

        baseHp = status.getMaxHp(bodyType.getBaseHealth());
        hp = baseHp;

        elements = status.getModifiedElements(baseBody.getCharElements());

        baseEvasiveness = status.getEvasiveness(0.0f);
        evasiveness = baseEvasiveness;

        baseAccuracy = status.getAccuracy(1.0f);
        accuracy = baseAccuracy;

        baseDmgPerc = status.getDmgPerc();
        dmgPerc = baseDmgPerc;

        attacks = new Attack[4];
        for(int i = 0; i < attacks.length; i++){
            attacks[i] = AttackArchive.getAttack(0);
        }
        for(Attack attack: attacks){
            attack = AttackArchive.getAttack(0);
        }
    }

    public String getName(){return charName;}
    public float getXPos(){return charTile.getX();}
    public float getYPos(){return charTile.getY();}
    public BotrGTile getCharTile() {return charTile;}
    public int getHp() {return hp;}
    public int getBaseHp() {return baseHp;}
    public float getEvasiveness() {return evasiveness;}
    public float getAccuracy() {return accuracy;}
    public float getDmgPerc() {return dmgPerc;}
    public Attack[] getAttacks() {return attacks;}
    public boolean checkIsPlayer(){return isPlayer;}

    public String getAttackName(int attackSlot) {return attacks[attackSlot].getDisplayName();}

    public String[] getAttackNames() {
        String[] names = new String[4];
        for(int i = 0; i < 4; i++){
            names[i] = attacks[i].getDisplayName();
        }
        return names;
    }
    public Element[] getElements(){
        return elements;
    }
    public BotrGState[] getStates(){
        return status.getStates();
    }
    public float[][] getStateValues(){
        return status.getStateValues();
    }

    public void setPos(float x, float y){charTile.setPos(x, y);}
    public void resetPos(BotrGStage stage){
        if(isPlayer) charTile.setPos(stage.getPlayerX(), stage.getPlayerY());
        else charTile.setPos(stage.getEnemyX(), stage.getEnemyY());
    }

    public void setIsPlayer(){isPlayer = true;}
    public void setIsEnemy(){isPlayer = false;}

    public void setHp(int newHp){hp = newHp;}
    public void reduceHp(int damage){
        hp -= damage;
        if(hp < 0) hp = 0;
        System.out.println("CHAR: Hp reduced! New Hp: " + hp);
    }
    public void increaseHp(int healing){
        hp += healing;
        if(hp > baseBody.getBaseHealth()) hp = baseBody.getBaseHealth();
    }

    public void setEvasiveness(float evasiveness) {this.evasiveness = evasiveness;}
    public void increaseEvasivness(float increase){evasiveness += increase;}

    public void setAttack(int slot, int attackId){
        attacks[slot] = AttackArchive.getAttack(attackId);
    }

    public void setAttacks(int[] attackIds){
        for(int i = 0; i < 4; i++){
            attacks[i] = AttackArchive.getAttack(attackIds[i]);
        }
    }

    public void addModifier(BotrGModifier newMod, float newModValue){
        status.addModifier(newMod, newModValue);
        elements = status.getModifiedElements(baseBody.getCharElements());
    }

    public void addState(BotrGState state, float[] values){
        status.addState(state, values);
    }
    public void removeState(BotrGState state){
        status.removeState(state);
    }

    public void updateChar(){
        System.out.println("CHAR: Char '" + charName + "' updated!");
        status.update(this);
    }

    public void draw(Batch batch){
        charTile.draw(batch);
    }


    public void printValues(){
        System.out.println("CHAR: ----------");
        System.out.println("CHAR: " + charName + " Status:");
        System.out.println("CHAR:  Base Body: " + baseBody.getDisplayName());
        System.out.println("CHAR:  Base Hp: " + baseHp);
        System.out.println("CHAR:  Base Evasiveness: " + baseEvasiveness);
        System.out.println("CHAR:  Base Accuracy: " + baseAccuracy);
        System.out.println("CHAR:  Base DmgPerc Modifier: " + baseDmgPerc);
        System.out.println("CHAR:  Elements: ");
        for (Element ele : elements) {
            System.out.println("CHAR:   " + "Element: " + ele.getType() + " Level: " + ele.getLvl());
        }
        System.out.println("CHAR:  Attacks: ");
        for(int i = 0; i < attacks.length; i++){
            System.out.println("CHAR:   " + i + ": " + attacks[i].getDisplayName());
        }
        System.out.println("CHAR:  Modifier: ");
        if(status.getMods().length == 0){
            System.out.println("CHAR:   NONE");
        } else {
            for(int i = 0; i < status.getMods().length; i++){
                System.out.println("CHAR:   " + i + ": " + status.getMods()[i]);
            }
        }
        System.out.println("CHAR: ----------");
    }
}
