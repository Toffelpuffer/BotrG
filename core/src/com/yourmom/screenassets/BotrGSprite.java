package com.yourmom.screenassets;

/**
 * Created by Ben on 10.07.2018.
 */

public class BotrGSprite {
    private String textureFileName;
    private int srcWidth;
    private int srcHeight;

    public BotrGSprite(String textureFileName, int srcWidth, int srcHeight){
        this.textureFileName = textureFileName;
        this.srcWidth = srcWidth;
        this.srcHeight = srcHeight;
    }

    public BotrGSprite(String textureFileName){
        this.textureFileName = textureFileName;
        srcWidth = 500;
        srcHeight = 500;
    }

    public void setTextureFileName(String textureFileName) {this.textureFileName = textureFileName;}

    public String getTextureFileName(){return textureFileName;}
    public int getSrcWidth() {return srcWidth;}
    public int getSrcHeight() {return srcHeight;}
}
