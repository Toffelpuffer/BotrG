package com.yourmom.screenassets;


/**
 * Created by Ben on 10.07.2018.
 */

public final class BotrGSpriteArchive {
    private BotrGSpriteArchive(){}

    public static BotrGSprite getSprite(int spriteId){
        switch(spriteId){
            //Characters 0-5999 (1:1)
            case 0: return new BotrGSprite("char/goku_front.png", 500,500);
            case 1000: return new BotrGSprite("char/goku_back.png", 500,500);
            case 1: return new BotrGSprite("char/jesus_front.png", 500, 500);
            case 1001: return new BotrGSprite("char/jesus_back.png", 500, 500);
            case 2: return new BotrGSprite("char/detlef_d_soost_front.png", 500, 500);
            case 1002: return new BotrGSprite("char/detlef_d_soost_back.png", 500, 500);
            case 3: return new BotrGSprite("char/bear_grylls_front.png", 500,500);
            case 1003: return new BotrGSprite("char/bear_grylls_back.png", 500,500);


            //Stages 6000-6999 (Stage-Background-Layer 16:9)
            case 6000: return new BotrGSprite("stage/stage0bg.png", 720, 1280);
            case 6001: return new BotrGSprite("stage/stage0ground.png", 500, 500);

            //Effects 7000-7999
            case 7000: return new BotrGSprite("effect/star.png", 31, 31);
            case 7001: return new BotrGSprite("effect/fist.png", 24, 24);


            //GUI's  9000+ (GUI 9:8, Button: 23:9, HpDisplay: 3:1, HpBar 20:1)
            case 9000: return new BotrGSprite("gui/guiMcBack.png", 207, 184);
            case 9001: return new BotrGSprite("gui/guiMcButton.png", 92, 36);
            case 9002: return new BotrGSprite("gui/guiMcButtonPressed.png", 92, 36);
            case 9003: return new BotrGSprite("gui/guiMcPlayerHpBox.png", 96, 32);
            case 9004: return new BotrGSprite("gui/guiMcEnemyHpBox.png", 96, 32);
            case 9005: return new BotrGSprite("gui/guiMcHpBarGreen.png", 80, 4);
            case 9006: return new BotrGSprite("gui/guiMcTextArrow.png", 11, 7);

            default: return new BotrGSprite("missing.png", 500,500);
        }
    }
}
