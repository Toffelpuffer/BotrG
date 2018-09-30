package com.yourmom.screenassets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Ben on 13.07.2018.
 */

public class BotrGText {
    private float x;
    private float y;
    private String displayedText;

    public BotrGText(String displayedText, float x, float y){
        this.x = x;
        this.y = y;
        this.displayedText = displayedText;
    }


    public String getDisplayedText(){ return displayedText; }
    public void setDisplayedText(String newText){
        displayedText = newText;
    }

    public void draw(Batch batch, BotrGFont botrGFont){
        botrGFont.draw(batch, displayedText, x, y);
    }
}
