package com.yourmom.screenassets;

import com.badlogic.gdx.graphics.g2d.Batch;

import static com.yourmom.game.BotrGStyle.screenHeight;
import static com.yourmom.game.BotrGStyle.screenWidth;

/**Retains all the values of a stage used in the Battle-Scene.
 * Created by Ben on 11.07.2018.
 */

public class BotrGStage {
    private float stageX = 0;
    private float stageY = 0;
    private float stageWidth = screenWidth;
    private float stageHeight = screenHeight;
    private float groundEnemyX;
    private float groundEnemyY;
    private float groundPlayerX;
    private float groundPlayerY;
    private float enemyX;
    private float enemyY;
    private float playerX;
    private float playerY;
    private BotrGTile backgroundTile;
    private BotrGTile groundEnemyTile;
    private BotrGTile groundPlayerTile;


    public BotrGStage(BotrGSprite backgroundSprite, BotrGSprite groundSprite){


        backgroundTile = new BotrGTile(0,0, backgroundSprite, CenterType.DEFAULT);
        groundEnemyTile = new BotrGTile(0,0, groundSprite);
        groundPlayerTile = new BotrGTile(0,0, groundSprite);

        setupStageValues();
    }

    public float getEnemyX() {return enemyX;}
    public float getEnemyY() {return enemyY;}
    public float getPlayerX() {return playerX;}
    public float getPlayerY() {return playerY;}

    void setStageSize(float stageWidth, float stageHeight){
        this.stageWidth = stageWidth;
        this.stageHeight = stageHeight;
        setupStageValues();
    }
    void setStagePos(float newStageX, float newStageY){
        stageX = newStageX;
        stageY = newStageY;
        updateStageValues();
    }
    public void resetStagePos(){
        setupStageValues();
    }

    public void setEnemyGroundPos(float newGroundX, float newGroundY){
        groundEnemyTile.setPos(stageX + newGroundX, stageY + newGroundY);}
    public void resetEnemyGroundPos(){
        groundEnemyTile.setPos(stageX + groundEnemyX, stageY + groundEnemyY);}
    public void setPlayerGroundPos(float newGroundX, float newGroundY){
        groundPlayerTile.setPos(stageX + newGroundX, stageY + newGroundY);}
    public void resetPlayerGroundPos(){
        groundEnemyTile.setPos(stageX + groundPlayerX, stageY + groundPlayerY);}

    public void setBackgroundPos(float newBgX, float newBgY){
        backgroundTile.setPos(newBgX, newBgY);
    }

    private void updateStageValues(){
        setBackgroundPos(stageX, stageY);
        setEnemyGroundPos(groundEnemyX, groundEnemyY);
        setPlayerGroundPos(groundPlayerX, groundPlayerY);
    }

    private void setupStageValues(){
        groundEnemyX = stageWidth * 0.7f;   //width  * 0.425f = 612  ;  612 + 400  = 1012 -> 0.7
        groundEnemyY = stageHeight * 0.73f; //height * 0.575f = 1472 ; 1472 + 400  = 1872 -> 0.73
        groundPlayerX = stageWidth * 0.2f;  //width  * -0.25f = -360 ; -360 + 650  = 290  -> 0.2
        groundPlayerY = stageHeight * 0.5f;//height * 0.31f  = 793  ;  793 + 650  = 1443 -> 0.56

        enemyX = groundEnemyX;
        enemyY = groundEnemyY  - stageHeight * 0.02f;

        playerX = stageWidth * 0.25f;
        playerY = stageHeight * 0.47f;

        backgroundTile.setSize((int)stageWidth, (int)stageHeight);
        groundEnemyTile.setSize((int)(stageWidth * 0.555f), (int)(stageWidth * 0.555f)); //800
        groundPlayerTile.setSize((int)(stageWidth * 1f), (int)(stageWidth * 1f));//(int)(stageWidth * 0.902f), (int)(stageWidth * 0.902f)); //1300

        updateStageValues();
    }

    public void draw(Batch batch){
        backgroundTile.draw(batch);
        groundEnemyTile.draw(batch);
        groundPlayerTile.draw(batch);
    }
}
