package com.yourmom.screenassets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.yourmom.game.BotrGStyle;

/**A clickable button.
 * Created by Ben on 13.07.2018.
 */

public class BotrGButton {
    private BotrGTile buttonTile;
    private int spriteNormalId;
    private int spritePressedId;
    private boolean touched;
    public boolean pressed;
    private boolean active;
    private BotrGText textObj;

    public BotrGButton(float x, float y, int spriteNormalId, int spritePressedId, int width, int height, String displayText){
        this.spriteNormalId = spriteNormalId;
        this.spritePressedId = spritePressedId;
        buttonTile = new BotrGTile(x, y, BotrGSpriteArchive.getSprite(spriteNormalId), width, height);
        touched = false;
        pressed = false;
        active = true;


        textObj = new BotrGText(displayText, x - width * 0.425f, y - height * 0.05f);
    }

    private void setTouched() {
        touched = true;
        buttonTile.setSpriteSameDim(BotrGSpriteArchive.getSprite(spritePressedId));
    }
    private void setNotTouched(){
        touched = false;
        buttonTile.setSpriteSameDim(BotrGSpriteArchive.getSprite(spriteNormalId));
    }

    public void setActive(){
        active = true;
        buttonTile.setSpriteSameDim(BotrGSpriteArchive.getSprite(spriteNormalId));
    }

    public void setInactive(){
        active = false;
        buttonTile.setSpriteSameDim(BotrGSpriteArchive.getSprite(spritePressedId));
    }

    public void setDisplayText(String newDisplayText){
        textObj.setDisplayedText(newDisplayText);
    }

    private boolean checkTouched(){
        if(     Gdx.input.getX()                            >= buttonTile.getX() - buttonTile.getDrawnSizeWidth()  / 2 &&
                Gdx.graphics.getHeight() - Gdx.input.getY() >= buttonTile.getY() - buttonTile.getDrawnSizeHeight() / 2 &&
                Gdx.input.getX()                            <= buttonTile.getX() + buttonTile.getDrawnSizeWidth()  / 2 &&
                Gdx.graphics.getHeight() - Gdx.input.getY() <= buttonTile.getY() + buttonTile.getDrawnSizeHeight() / 2){
            return true;
        }
        return false;
    }

    public void update() {
        if(active) {
            if (pressed) pressed = false;

            if (Gdx.input.justTouched() && checkTouched()) {
                setTouched();
            } else if (touched && !checkTouched()) {
                setNotTouched();
            } else if (touched && !Gdx.input.isTouched()) {
                pressed = true;
                setNotTouched();
            }
        }
    }

    public void draw(Batch batch){
        buttonTile.draw(batch);
        if(active) textObj.draw(batch, BotrGStyle.getActiveFontSet().getButtonFont());
    }
}
