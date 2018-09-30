package com.yourmom.screenassets;

import com.yourmom.botrgchar.Attack;
import com.yourmom.botrgchar.AttackArchive;

/**
 * Created by Ben on 14.07.2018.
 */

public class BotrGTextConvertor {
    public final static int LINE_LENGTH = 27;

    private BotrGTextConvertor(){}

    public static String[] stringToLines(String newLines){
        String[] setLines = {"","","",""};
        char[] charLine = newLines.toCharArray();
        int nextLineStart = 0;
        int setLinesCnt = 0;

        //loops through char array
        for(int i = 0; i < newLines.length(); i++){
            //when a 21th or last char is reached
            if(i - nextLineStart == LINE_LENGTH){
                //loop back until 'space'
                for(int j = i; j > 0; j--){
                    if(charLine[j] == ' '){
                        //put all chars before this 'space' into setLines
                        char[] foundLine = new char[j - nextLineStart];
                        System.arraycopy(charLine, nextLineStart, foundLine, 0, foundLine.length);
                        setLines[setLinesCnt] = String.valueOf(foundLine);
                        setLinesCnt++;

                        nextLineStart = j + 1;
                        break;
                    }
                }

            }
            if(i + 1 == newLines.length()){
                        //put all chars until the end into setLines
                        char[] foundLine = new char[i - nextLineStart + 1];
                        System.arraycopy(charLine,  nextLineStart, foundLine, 0, foundLine.length);
                        setLines[setLinesCnt] = String.valueOf(foundLine);
                        //nextLineStart = j + 1;
            }
            if(setLinesCnt > 3) break;
        }
        return setLines;
    }

    public static String replaceTags(String string, BotrGSceneBattle battleScene, Attack attack){
        String convertedString = string;

        convertedString = convertedString.replace("<ATTNAME>", battleScene.getAttackingChar().getName());
        convertedString = convertedString.replace("<DEFNAME>", battleScene.getDefendingChar().getName());
        convertedString = convertedString.replace("<ATTACKNAME>", attack.getDisplayName());

        return convertedString;
    }
    public static String replaceTags(String string, BotrGSceneBattle battleScene){
        return replaceTags(string, battleScene, AttackArchive.getAttack(0));
    }
}


