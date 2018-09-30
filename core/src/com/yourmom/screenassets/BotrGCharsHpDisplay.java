package com.yourmom.screenassets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.yourmom.game.BotrGStyle;

import static com.yourmom.game.BotrGStyle.screenHeight;
import static com.yourmom.game.BotrGStyle.screenWidth;

/**
 * Created by Ben on 19.07.2018.
 */

public class BotrGCharsHpDisplay {

    private BotrGTile playerBox;
    private BotrGTile enemyBox;
    private BotrGTile playerHpBar;
    private BotrGTile enemyHpBar;
    private Vector2 playerHpBarPos;
    private Vector2 enemyHpBarPos;
    private BotrGText playerName;
    private BotrGText enemyName;

    BotrGCharsHpDisplay(){
        setup(screenWidth, screenHeight);
    }

    public void setPlayerHpBarLength(double newSizeInPerc){
        playerHpBar.setSizeWidth((float)(newSizeInPerc * 0.3864734f * screenWidth));
    }

    public void setEnemyHpBarLength(double newSizeInPerc){
        enemyHpBar.setSizeWidth((float)(newSizeInPerc * 0.3864734f * screenWidth));
    }

    public void reducePlayerHpBar(float perc){
        playerHpBar.setSizeWidth((int)(playerHpBar.getDrawnSizeWidth() - screenWidth * 0.3864734f * perc));
    }

    public void setPlayerName(String newPlayerName){ playerName.setDisplayedText(newPlayerName); }
    public void setEnemyName(String newEnemyName){ enemyName.setDisplayedText(newEnemyName); }

    public void update(){

    }

    public void draw(Batch batch){
        playerBox.draw(batch);
        enemyBox.draw(batch);

        playerHpBar.draw(batch);
        enemyHpBar.draw(batch);


        if(playerName.getDisplayedText().length() < 12){
            playerName.draw(batch, BotrGStyle.getActiveFontSet().getHpInfoBoxFontN());
        }else {
            playerName.draw(batch, BotrGStyle.getActiveFontSet().getHpInfoBoxFontS());
        }

        if(enemyName.getDisplayedText().length() < 12){
            enemyName.draw(batch, BotrGStyle.getActiveFontSet().getHpInfoBoxFontN());
        }else {
            enemyName.draw(batch, BotrGStyle.getActiveFontSet().getHpInfoBoxFontS());
        }

    }

    private void setup(float screenWidth, float screenHeight){
        Vector2 playerBoxPos = new Vector2(screenWidth * 0.53f, screenHeight * 0.575f);
        Vector2 enemyBoxPos = new Vector2(screenWidth * 0.01f, screenHeight * 0.825f);
        playerBox = new BotrGTile(playerBoxPos.x, playerBoxPos.y, BotrGSpriteArchive.getSprite(9003),
                (int)(screenWidth * 0.4637681f), (int)(screenWidth * 0.1545893f), CenterType.DEFAULT);
        enemyBox = new BotrGTile(enemyBoxPos.x, enemyBoxPos.y, BotrGSpriteArchive.getSprite(9004),
                (int)(screenWidth * 0.4637681f), (int)(screenWidth * 0.1545893f), CenterType.DEFAULT);

        playerHpBarPos = new Vector2(screenWidth * 0.5885f, screenHeight * 0.586f);
        enemyHpBarPos = new Vector2(screenWidth * 0.029f, screenHeight * 0.836f);

        playerHpBar = new BotrGTile(playerHpBarPos.x, playerHpBarPos.y, BotrGSpriteArchive.getSprite(9005), (int)(screenWidth * 0.3864734f), (int)(screenWidth * 0.0193236f), CenterType.DEFAULT);
        enemyHpBar = new BotrGTile(enemyHpBarPos.x, enemyHpBarPos.y, BotrGSpriteArchive.getSprite(9005), (int)(screenWidth * 0.3864734f), (int)(screenWidth * 0.0193236f), CenterType.DEFAULT);

        playerName = new BotrGText("", screenWidth * 0.5885f, screenHeight * 0.621f);
        enemyName = new BotrGText("", screenWidth * 0.029f, screenHeight * 0.871f);
    }
}
