package com.yourmom.screenassets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by Ben on 13.07.2018.
 */

public class BotrGFont {
        private FreeTypeFontGenerator fontGen;
        private FreeTypeFontGenerator.FreeTypeFontParameter fontPar;
        private BitmapFont font;

        private int fontSize;



    public BotrGFont(String fontFileName, int fontSize) {
        this.fontSize = fontSize;

        fontGen = new FreeTypeFontGenerator(Gdx.files.internal(fontFileName));
        fontPar = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontPar.size = this.fontSize;
        fontPar.color = Color.BLACK;

        updateFont();
    }


    public void setFontParameters(String newFont){
        fontGen = new FreeTypeFontGenerator(Gdx.files.internal(newFont));
    }

    public void updateFont(){
        font = fontGen.generateFont(fontPar);
    }

    public void draw(Batch batch, String displayedText, float x, float y){
        font.draw(batch, displayedText, x, y);
    }
}
