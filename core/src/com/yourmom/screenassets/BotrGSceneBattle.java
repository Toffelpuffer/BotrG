package com.yourmom.screenassets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.yourmom.botrgchar.BotrGState;

import static com.yourmom.game.BotrGStyle.screenHeight;
import static com.yourmom.game.BotrGStyle.screenWidth;

/**The scene in which the player fights his enemys.
 * Created by Ben on 11.07.2018.
 */

public class BotrGSceneBattle {
    private BotrGStage stage;
    private BotrGGuiBattle gui;
    private BotrGChar enemyChar;
    private BotrGChar playerChar;
    private boolean playerIsAttacking = true;

    public static BotrGEffectTile[] effects;

    public BotrGSceneBattle(int stageId, BotrGChar playerChar, BotrGChar enemyChar){
        stage = BotrGStageArchive.getStage(stageId);
        stage.setStageSize(screenWidth, screenHeight);
        stage.setStagePos(0,0);

        this.playerChar = playerChar;
        this.enemyChar = enemyChar;
        this.playerChar.setPos(stage.getPlayerX(), stage.getPlayerY());
        this.enemyChar.setPos(stage.getEnemyX(), stage.getEnemyY());

        gui = new BotrGGuiBattle();

        gui.hpBoxes.setPlayerName(this.playerChar.getName());
        gui.hpBoxes.setEnemyName(this.enemyChar.getName());

        effects = new BotrGEffectTile[0];

        //tests:
        this.playerChar.setAttack(0, 1);
        this.playerChar.setAttack(1, 2);
        this.playerChar.setAttack(3, 999);

        this.enemyChar.setAttack(0, 1);
        this.enemyChar.setAttack(1, 2);

        this.playerChar.addState(BotrGState.POISON, new float[]{2.0f, 30.0f});
        //this.playerChar.addState(BotrGState.LEGO, new float[]{1.0f, 0.5f, 20.0f, 0.0f});

        //this.enemyChar.addState(BotrGState.LEGO, new float[]{1.0f, 1.0f, 20.0f, 0.0f});

        playerChar.printValues();
        enemyChar.printValues();
    }

    public BotrGGuiBattle getGui(){
        return gui;
    }
    public BotrGStage getStage(){ return stage; }
    public BotrGChar getPlayerChar(){return playerChar;}
    public BotrGChar getEnemyChar() {return enemyChar;}
    public BotrGChar getAttackingChar(){return playerIsAttacking ? playerChar : enemyChar;}
    public BotrGChar getDefendingChar(){return playerIsAttacking ? enemyChar : playerChar;}
    public BotrGChar getChar(boolean isPlayer){return isPlayer ? playerChar : enemyChar;}

    public void setPlayerIsAttacking(boolean playerIsAttacking) {this.playerIsAttacking = playerIsAttacking;}
    public void setEffects(BotrGEffectTile[] newEffects){
        effects = newEffects;
    }
    public void resetEffects(){effects = new BotrGEffectTile[0];}

    public void update(){

        gui.update();
    }

    public void draw(Batch batch) {
        stage.draw(batch);

        for(int i = 0; i < effects.length; i++)
            if(effects[i].isInfront()) effects[i].draw(batch);
        playerChar.draw(batch);
        enemyChar.draw(batch);
        for(int i = 0; i < effects.length; i++)
            if(!effects[i].isInfront()) effects[i].draw(batch);

        gui.draw(batch);
    }
}
