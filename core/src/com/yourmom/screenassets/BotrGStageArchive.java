package com.yourmom.screenassets;

/**
 * Created by Ben on 11.07.2018.
 */

final public class BotrGStageArchive {


    public static BotrGStage getStage(int stageId){
        switch(stageId){
            //TODO: add stages
            case 0: return new BotrGStage(BotrGSpriteArchive.getSprite(6000), BotrGSpriteArchive.getSprite(6001));

            default: return new BotrGStage(BotrGSpriteArchive.getSprite(6000), BotrGSpriteArchive.getSprite(6001));
        }
    }

}
