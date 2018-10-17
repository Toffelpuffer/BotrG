package com.yourmom.botrgchar;

import com.yourmom.screenassets.BotrGChar;

import static com.yourmom.botrgchar.BotrGModifier.getRandomPostName;
import static com.yourmom.botrgchar.BotrGModifier.getRandomPreName;
import static com.yourmom.botrgchar.BotrGModifier.isElementalModDown;
import static com.yourmom.botrgchar.BotrGModifier.isElementalModUp;
import static com.yourmom.botrgchar.Element.checkForElement;
import static com.yourmom.botrgchar.Element.modToEle;

/**
 * Created by Ben on 01.08.2018.
 */

public class BotrGStatus {
    private BotrGModifier[] mods;
    private float[] modValues;
    private BotrGState[] states;
    //private float[][] stateValues;


    public BotrGStatus(){
        mods = new BotrGModifier[0];
        modValues = new float[0];
        states = new BotrGState[0];
    }

    public void addModifier(BotrGModifier newMod, float newModValue){
        BotrGModifier[] newMods = new BotrGModifier[mods.length + 1];
        float[] newModValues = new float[modValues.length + 1];

        System.arraycopy(mods, 0, newMods, 0, mods.length);
        newMods[mods.length] = newMod;
        System.arraycopy(modValues, 0, newModValues, 0, modValues.length);
        newModValues[modValues.length] = newModValue;

        mods = newMods;
        modValues = newModValues;

        System.out.println("STATUS: Mod Added! New Mods:");
        printMods(mods, modValues);
    }

    private void printMods(BotrGModifier[] mods, float[] modValues){
        for(int i = 0; i < mods.length; i++){
            System.out.println("STATUS:  Mod: " + mods[i] + " Value: " + modValues[i]);
        }
    }

    public void addState(BotrGState newState){
        BotrGState[] newStates = new BotrGState[states.length + 1];

        System.arraycopy(states, 0, newStates, 0, states.length);
        newStates[states.length] = newState;

        states = newStates;

        System.out.println("STATUS: State '" + newState + "' Added! New States:");
        printStates(states);
    }

    public void removeState(BotrGState state){
        BotrGState[] newStates = new BotrGState[states.length - 1];
        int currentState = 0;
        for(int i = 0; i < states.length; i++){
            if(states[i] != state){
                newStates[currentState] = states[i];
                currentState++;
            }
        }

        states = newStates;

        System.out.println("STATUS: State '" + state + "' Removed! New active States:");
        printStates(states);
    }

    private void printStates(BotrGState[] states){
        for (BotrGState state : states) {
            System.out.print("STATUS:  State: " + state + " Values: ");
            for (int j = 0; j < state.getValues().length; j++) {
                System.out.print(state.getValues()[j] + " ");
            }
            System.out.println("");
        }
    }

    public void setModifiers(BotrGModifier[] mods, float[] modValues){
        this.mods = mods;
        this.modValues = modValues;
    }

    public BotrGModifier[] getMods() {
        return mods;
    }
    public float[] getModValues() {
        return modValues;
    }
    public BotrGState[] getStates() {
        return states;
    }

    public int getMaxHp(int baseHealth) {
        int maxHp = baseHealth;
        for(int i = 0; i < mods.length; i++){
            if(mods[i] == BotrGModifier.MAXHP_UP) maxHp += modValues[i];
            if(mods[i] == BotrGModifier.MAXHP_DOWN) maxHp -= modValues[i];
        }
        System.out.println("STATUS: New baseHp: " + maxHp);
        return maxHp;
    }

    public float getEvasiveness(float baseEvasiveness){
        float evasiveness = baseEvasiveness;
        for(int i = 0; i < mods.length; i++){
            if(mods[i] == BotrGModifier.EVASIVENESS_UP) evasiveness += modValues[i];
            if(mods[i] == BotrGModifier.EVASIVENESS_DOWN) evasiveness -= modValues[i];
        }
        if(evasiveness < 0.0f) evasiveness = 0.0f;
        System.out.println("STATUS: new baseEvasiveness: " + evasiveness);
        return evasiveness;
    }

    public float getAccuracy(float baseAccuracy){
        float accuracy = baseAccuracy;
        for(int i = 0; i < mods.length; i++){
            if(mods[i] == BotrGModifier.ACCURACY_UP) accuracy += modValues[i];
            if(mods[i] == BotrGModifier.ACCURACY_DOWN) accuracy -= modValues[i];
        }
        if(accuracy < 0.0f) accuracy = 0.0f;
        System.out.println("STATUS: New baseAccuracy: " + accuracy);
        return accuracy;
    }

    public float getDmgPerc(){
        float dmgPerc = 1.0f;
        for(int i = 0; i < mods.length; i++){
            if(mods[i] == BotrGModifier.DMG_UP) dmgPerc += modValues[i];
            if(mods[i] == BotrGModifier.DMG_DOWN) dmgPerc -= modValues[i];
        }
        if(dmgPerc < 0.0f) dmgPerc = 0.0f;
        System.out.println("STATUS: New dmgPerc: " + dmgPerc);
        return dmgPerc;
    }

    /**
     *
     * @param baseBodyElements
     * @return an Element array
     */
    public Element[] getModifiedElements(Element[] baseBodyElements){
        Element[] finalElements = baseBodyElements;

        for(int i = 0; i < mods.length; i++){
            //if mod is element up && char does already have that element
            if(isElementalModUp(mods[i]) && checkForElement(modToEle(mods[i]), finalElements)){
                //rise value
                for(Element element: finalElements){
                    if(element.getType() == modToEle(mods[i])){
                        element.setLvl(element.getLvl() + (int)modValues[i]);
                        break;
                    }
                }

                System.out.println("STATUS: Raised Element! Elements:");
                printElements(finalElements);

            //if mod is element up && char doesn't already have that element
            } else if(isElementalModUp(mods[i]) && !checkForElement(modToEle(mods[i]), finalElements)) {
                //add element
                Element[] newElements = new Element[finalElements.length + 1];
                System.arraycopy(finalElements, 0, newElements, 0, finalElements.length);
                newElements[newElements.length - 1] = new Element(modToEle(mods[i]), (int) modValues[i]);
                finalElements = newElements;

                System.out.println("STATUS: Added Element! New Elements:");
                printElements(finalElements);
            }
        }

        for(int i = 0; i < mods.length; i++){
            if(isElementalModDown(mods[i])){
                for(Element element: finalElements){
                    if(element.getType() == modToEle(mods[i])){
                        element.setLvl(element.getLvl() - (int)modValues[i]);
                        if(element.getLvl() < 0) element.setLvl(0);
                        break;
                    }
                }

                System.out.println("STATUS: Reduced Element! Elements:");
                printElements(finalElements);
            }
        }

        finalElements = clearElements(finalElements);

        System.out.println("STATUS: Final Elements:");
        printElements(finalElements);

        return finalElements;
    }

    /**
     * Removes all elements with a lvl of 0 or lower from the given array.
     */
    private Element[] clearElements(Element[] elements){
        int reducedElements = 0;
        for(Element ele: elements){
            if(ele.getLvl() <= 0)  reducedElements++;
        }

        Element[] finalElements = new Element[elements.length - reducedElements];

        int eleCnt = 0;

        for(Element ele: elements){
            if(ele.getLvl() > 0){
                finalElements[eleCnt] = ele;
                eleCnt++;
            }
        }

        return finalElements;
    }

    private void printElements(Element[] elements){
        for(Element ele : elements){
            System.out.println("STATUS:  Ele: " + ele.getType() + " Lvl: " + ele.getLvl());
        }
    }

    public String generateCharName(String charName, String charShortName){
        String finalName = charShortName;
        String[] prePostNames = getRandomPrePostNames();

        if(prePostNames[0].equals("") && prePostNames[1].equals("")) finalName = charName;
        else {
            if(!prePostNames[0].equals("")) prePostNames[0] = prePostNames[0] + " ";
            if(!prePostNames[1].equals("")) prePostNames[1] = " " + prePostNames[1];

            finalName = prePostNames[0] + finalName + prePostNames[1];
        }

        System.out.println("STATUS: Final Name: " + finalName);
        return finalName;
    }


    private String[] getRandomPrePostNames(){
        String[] finalPrePostNames = {"", ""};

        System.out.println("STATUS: Mods length: " + mods.length);
        switch(mods.length){
            case 0: break;
            case 1:
                int rdmBool = (int)(Math.random() * 2);
                if(rdmBool == 0) finalPrePostNames[0] = getRandomPreName(mods[0]);
                else if(rdmBool == 1) finalPrePostNames[1] = getRandomPostName(mods[0]);
                break;
            default:
                int firstMod = (int)(Math.random() * mods.length);
                System.out.println("STATUS: First Name Def Mod: " + "Id: " + firstMod + " Mod: " + mods[firstMod]);

                int secondMod = firstMod;
                while(secondMod == firstMod){
                    secondMod = (int)(Math.random() * mods.length);
                }

                System.out.println("STATUS: Second Name Def Mod: " + "Id: " + secondMod + " Mod: " + mods[secondMod]);

                finalPrePostNames[0] = getRandomPreName(mods[firstMod]);
                finalPrePostNames[1] = getRandomPostName(mods[secondMod]);
        }
        System.out.println("STATUS: Name Mods: Pre: " + finalPrePostNames[0] + " Post: " + finalPrePostNames[1]);
        return finalPrePostNames;
    }

    public void update(BotrGChar character){
        BotrGState.turnEnd(character);
    }
}