package com.yourmom.events;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.yourmom.botrgchar.BodyArchive;
import com.yourmom.botrgchar.BotrGModifier;
import com.yourmom.screenassets.BotrGSceneBattle;
import com.yourmom.screenassets.BotrGChar;


import static com.yourmom.game.BotrGStyle.screenHeight;
import static com.yourmom.game.BotrGStyle.screenWidth;

/**Queues and plays events in the game based on inputs.
 * Created by Ben on 13.07.2018.
 */
public class BotrGEventCoordinator {
        private BotrGSceneBattle battleScene;

        private BotrGEvent activeEvent;
        private BotrGEvent queuedEvent;

        private boolean animationActive;


        public BotrGEventCoordinator(){
            animationActive = true;

            setBattleScene(SceneType.BATTLE);

            activeEvent = new BotrGEventPlayerChoice();
            activeEvent.startEvent(battleScene);
        }


        public void setBattleScene(SceneType scene){
            switch(scene){

                case BATTLE:
                        battleScene = new BotrGSceneBattle(0
                                , new BotrGChar(0,0, BodyArchive.getBody(4000),
                                (int)(screenWidth * 0.625f), (int)(screenHeight * 0.3516f), true,
                                new BotrGModifier[]{BotrGModifier.GOD_DOWN, BotrGModifier.SWOLE_UP, BotrGModifier.ADDNAMEPARTS},
                                new float[] {1.0f, 3.0f, 0.0f})
                                , new BotrGChar(0,0, BodyArchive.getBody(4001),
                                (int)(screenWidth * 0.4513f), (int)(screenHeight * 0.2539f), false,
                                new BotrGModifier[]{BotrGModifier.ADDNAMEPARTS},
                                new float[] {0.0f})
                        );
                    break;

                //default: battleScene = ...;
            }

        }

        public void setNextEvent(BotrGEvent nextEvent){
            queuedEvent = nextEvent;
        }

        public void nextEvent(){
            animationActive = false;
            activeEvent = queuedEvent;
            activeEvent.startEvent(battleScene);
            animationActive = true;
        }

        public void update(){
            activeEvent.update(battleScene, this);
            battleScene.update();
        }

        /**
         * called every active animation frame
         */
        public void updateAnimation(){
            if(animationActive){
                activeEvent.updateAnimation(battleScene);
            }
        }

        public void draw(Batch batch){
            battleScene.draw(batch);
        }
}


