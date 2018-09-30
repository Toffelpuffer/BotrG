package com.yourmom.screenassets;

/**
 * Created by Ben on 28.07.2018.
 */

public class BotrGEffectTile extends BotrGTile {
    private boolean infrontChar;

    public BotrGEffectTile(float x, float y, BotrGSprite sprite, int sizeWidth, int sizeHeight) {
        super(x, y, sprite, sizeWidth, sizeHeight, CenterType.CENTERED);

        setAlpha(0.0f);
    }


    public void setInfrontPlayer(boolean infrontPlayer) {
        this.infrontChar = infrontPlayer;
    }

    public boolean isInfront(){
        return infrontChar;
    }
}
