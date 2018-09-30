package com.yourmom.screenassets;

/**
 * Created by Ben on 11.07.2018.
 */

public class BotrGTile extends ScreenActor {

    public BotrGTile(float x, float y, BotrGSprite sprite){
        super(x, y, sprite.getTextureFileName(), sprite.getSrcWidth(), sprite.getSrcHeight(), CenterType.CENTERED);
    }

    public BotrGTile(float x, float y, BotrGSprite sprite, CenterType centerType){
        super(x, y, sprite.getTextureFileName(), sprite.getSrcWidth(), sprite.getSrcHeight(), centerType);
    }

    public BotrGTile(float x, float y, BotrGSprite sprite, int sizeWidth, int sizeHeight){
        super(x, y, sprite.getTextureFileName(), sprite.getSrcWidth(), sprite.getSrcHeight(), sizeWidth, sizeHeight, CenterType.CENTERED);
    }

    public BotrGTile(float x, float y, BotrGSprite sprite, int sizeWidth, int sizeHeight, CenterType centerType){
        super(x, y, sprite.getTextureFileName(), sprite.getSrcWidth(), sprite.getSrcHeight(), sizeWidth, sizeHeight, centerType);
    }

    public BotrGTile(float x, float y, BotrGSprite sprite, int heightAndWidth, CenterType centerType){
        super(x, y, sprite.getTextureFileName(), sprite.getSrcWidth(), sprite.getSrcHeight(), heightAndWidth, heightAndWidth, centerType);
    }

    public void setSpriteSameDim(BotrGSprite newSprite){
        setTextureFileName(newSprite.getTextureFileName());
    }
}
