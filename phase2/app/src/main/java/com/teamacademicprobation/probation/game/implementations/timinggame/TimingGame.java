package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.ScoreBoard;
import com.teamacademicprobation.probation.game.implementations.timinggame.meter.Meter;
import com.teamacademicprobation.probation.player.PlayerAccess;
import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.player.PlayerStatsAccess;


// TODO: ADD DOCUMENTATION
// TODO: IMPLEMENT BONUS BOX
// TODO: IMPLEMENT CURR LEVEL, ENEMY NAME GENERATOR
// TODO: IMPLEMENT SAVE / PREFERENCES
// TODO: ADD MORE LEVELS?
// TODO: CLEAN UP THE CODE

/**
 * Timing game version 2! When the cursor is at the area, tap to shoot a bullet. Each enemy's
 * health is increased by 1 when you destroy the previous one! The boss has 7 health.
 *
 */

public class TimingGame {

    private static final int WAITING_FRAMES = 20;
    private int currWaitingFrame = 0;

    private Meter meter;
    private int currLevel = 1;
    static final int BOSS_LEVEL = 5;
    private ScoreBoard scoreBoard;
    private static final String GAME_ID = "TimingGame";

    private PlayerStatsAccess playerAccess;
    private String currPlayerID;

    private PlayerShip playerShip;
    private EnemyShip enemyShip;
    private ShipFactory shipFactory;
    private Bullet currBullet;

    private boolean completed;

    private int screenWidth;
    private int screenHeight;
    private TimingGameStyle gameStyle;

    TimingGame(int screenWidth, int screenHeight, String currPlayerID, Context context) {

        // Change for preferences
         this.gameStyle = new TimingGameStyle(TimingGameStyles.STYLE3); // CHANGE THIS FOR DIFF COLOR

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.meter = new Meter(screenWidth, screenHeight, gameStyle);
        this.scoreBoard = new TimingGameScoreBoard(screenWidth, screenHeight);

        this.shipFactory = new ShipFactory(context);

        this.playerShip = shipFactory.createPlayerShip(screenWidth, screenHeight);
        this.enemyShip = shipFactory.createEnemyShip(screenWidth, screenHeight, currLevel);


        this.playerAccess = new PlayerManager();
        this.currPlayerID = currPlayerID;

    }

    public void update() {
        this.meter.update();
        if(this.currBullet != null && this.currBullet.contact()){
            damageTarget();
            this.currBullet = null;
        }
        if(this.enemyShip.isDestroyed()){
            if(this.currWaitingFrame >= WAITING_FRAMES){
                this.enemyShip = shipFactory.createEnemyShip(screenWidth, screenHeight, ++this.currLevel);
                this.currWaitingFrame = 0;
            }else {
                this.currWaitingFrame++;
            }
        }
        if(this.playerShip.isDestroyed()){
            if(this.currWaitingFrame >= WAITING_FRAMES){
                this.completed = true;
            }
            this.currWaitingFrame++;
        }
    }

    private void damageTarget() {
        if(this.currBullet instanceof PlayerBullet){
            this.enemyShip.takeDamage();
            if(this.enemyShip.getHealth() == 0){
                this.enemyShip.setDestroyed(true);
                this.updateScore();
            }
        }
        else{
            this.playerShip.setDestroyed(true);
        }
    }


    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK); // resets the screen.
        this.meter.draw(canvas);
        this.scoreBoard.draw(canvas);
        this.playerShip.draw(canvas);
        this.enemyShip.draw(canvas);

        if(this.currBullet != null){
            currBullet.draw(canvas);
        }
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void onTouch() {
        if(this.meter.cursorNearTarget()){
            this.currBullet = new PlayerBullet(this.screenWidth, this.screenHeight, gameStyle);
        }
        else{
            this.currBullet = new EnemyBullet(this.screenWidth, this.screenHeight, gameStyle);
        }

        this.meter.newTarget();
    }

    public int getScore(){
        return this.scoreBoard.getScore();
    }

    private void updateScore(){
        this.scoreBoard.earnPoint();
        if(this.scoreBoard.getScore() > BOSS_LEVEL){
            this.completed = true;
        }
    }
}
