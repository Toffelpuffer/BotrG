package com.yourmom.screenassets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/** Retains values and methods to draw an texture on screen.
 * Created by Ben on 10.07.2018.
 */

public abstract class ScreenActor {
    private String textureFileName;
    private Texture tex;
    private Sprite spr;
    private float x;
    private float y;
    private int srcWidth;
    private int srcHeight;
    private float sizeWidth;
    private float sizeHeight;
    private float drawnSizeWidth;
    private float drawnSizeHeight;
    private CenterType centerType;


    /**
     * @param x x position
     * @param y y position
     * @param textureFileName texture file name
     * @param srcWidth source width of the texture file
     * @param srcHeight source height of the texture file
     * @param sizeWidth displayed size width
     * @param sizeHeight displayed size height
     */
    ScreenActor(float x, float y, String textureFileName, int srcWidth, int srcHeight, int sizeWidth, int sizeHeight, CenterType centerType){
        this.x = x;
        this.y = y;
        this.textureFileName = textureFileName;
        tex = new Texture(Gdx.files.internal(textureFileName));

        this.srcWidth = srcWidth;
        this.srcHeight = srcHeight;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
        drawnSizeWidth = sizeWidth;
        drawnSizeHeight = sizeHeight;

        this.centerType = centerType;

        spr = new Sprite(tex, 0, 0, srcWidth, srcHeight);
        spr.setPosition(this.x, this.y);
        spr.setSize(drawnSizeWidth, drawnSizeHeight);
        spr.setOrigin(0,0);

        updatePos();
    }

    ScreenActor(float x, float y, String textureFileName, int srcWidth, int srcHeight, CenterType centerType){
        this.x = x;
        this.y = y;
        this.textureFileName = textureFileName;
        tex = new Texture(Gdx.files.internal(textureFileName));

        this.srcWidth = srcWidth;
        this.srcHeight = srcHeight;
        this.sizeWidth = srcWidth;
        this.sizeHeight = srcHeight;
        drawnSizeWidth = sizeWidth;
        drawnSizeHeight = sizeHeight;

        this.centerType = centerType;

        spr = new Sprite(tex, 0, 0, srcWidth, srcHeight);
        spr.setPosition(this.x, this.y);
        spr.setSize(drawnSizeWidth, drawnSizeHeight);
        spr.setOrigin(0,0);

        updatePos();
    }

    public void setTextureFileName(String newFileName){textureFileName = newFileName; updateAll();}
    public void setX(float newX){x = newX; updatePos();}
    public void setY(float newY){y = newY; updatePos();}
    public void setPos(float newX, float newY){x = newX; y = newY; updatePos();}
    public void setPos(Vector2 newPos){x = newPos.x; y = newPos.y; updatePos();}
    public void setSizeWidth(float sizeWidth){drawnSizeWidth = sizeWidth; updateSize();}
    public void setSizeHeight(float sizeHeight){drawnSizeHeight = sizeHeight; updateSize();}
    public void setSize(float newSizeWidth, float newSizeHeight){
        drawnSizeWidth = newSizeWidth;
        drawnSizeHeight = newSizeHeight;
        updateSize();
    }
    public void resetSize(){
        setSize(sizeWidth, sizeHeight);
        updateSprite();
    }
    public void setAlpha(float newAlpha){
        spr.setAlpha(newAlpha);
    }

    public void setColor(float r, float g, float b, float a){
        spr.setColor(r, g, b, a);
    }
    public void resetColor(){spr.setColor(Color.WHITE);}

    public String getTextureFileName(){return textureFileName;}
    public float getX(){return x;}
    public float getY(){return y;}
    public float getDrawnSizeWidth(){return drawnSizeWidth;}
    public float getDrawnSizeHeight(){return drawnSizeHeight;}

    public void updatePos(){
        switch(centerType){
            case CENTERED: spr.setPosition(x - drawnSizeWidth / 2, y - drawnSizeHeight / 2);
                break;
            case WIDTH_CENTERED: spr.setPosition(x - drawnSizeWidth / 2, y);
                break;
            case DEFAULT: spr.setPosition(x, y);
                break;
            default: spr.setPosition(x, y);
        }
    }
    private void updateSize(){spr.setSize(drawnSizeWidth, drawnSizeHeight);}
    private void updateSprite(){
        tex = new Texture(Gdx.files.internal(textureFileName));
        spr = new Sprite(tex, 0,0, srcWidth, srcHeight);
    }

    private void updateAll(){
        updateSprite();
        updatePos();
        updateSize();
    }


    public void draw(Batch batch){
        spr.draw(batch);
    }
}

enum CenterType{CENTERED, WIDTH_CENTERED, DEFAULT}