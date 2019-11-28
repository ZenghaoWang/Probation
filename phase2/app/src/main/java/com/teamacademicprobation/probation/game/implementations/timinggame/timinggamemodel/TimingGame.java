package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.content.Context;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;

import java.util.ArrayList;
import java.util.List;



// TODO: IMPLEMENT BONUS BOX
// TODO: IMPLEMENT SAVE / PREFERENCES
// TODO: CLEAN UP THE CODE

/**
 * Timing game version 2! When the cursor is at the area, tap to shoot a bullet. Each enemy's
 * health is increased by 1 when you destroy the previous one! The boss has 7 health for now.
 */

public class TimingGame implements Drawable {

    /**
     * Number of frames to wait before any action is done.
     */
    private static final int WAITING_FRAMES = 20;
    /**
     * The current frame when waiting.
     */
    private int currWaitingFrame = 0;


    /**
     * The current level of the game.
     */
    private int currLevel = 1;
    /**
     * The end level of the game.
     */
    static final int BOSS_LEVEL = 3;


    private TimingGameScoreBoard scoreBoard;


    // ===== Objects for the game =====
    private Meter meter;
    private PlayerShip playerShip;
    private EnemyShip enemyShip;
    private ShipFactory shipFactory;
    private Bullet currBullet;

    /**
     * Represents if the stage has been completed or not.
     */
    private boolean stageCompleted;


    private int screenWidth;
    private int screenHeight;
    private TimingGameStyle gameStyle;

    /**
     * Initializes a new timing game.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     */
    public TimingGame(int screenWidth, int screenHeight) {

        // Change for preferences
        this.gameStyle = new TimingGameStyle(TimingGameStyles.STYLE3); // CHANGE THIS FOR DIFF COLOR

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.meter = new Meter(screenWidth, screenHeight, gameStyle);
        this.scoreBoard = new TimingGameScoreBoard(screenWidth, screenHeight);
    }

    /**
     * Initializes the ships.
     *
     * @param context The context to be able to get resources.
     */
    public void buildShips(Context context) {
        this.shipFactory = new ShipFactory(context, gameStyle);
        this.playerShip = shipFactory.createPlayerShip(screenWidth, screenHeight);
        this.enemyShip = shipFactory.createEnemyShip(screenWidth, screenHeight, currLevel);
    }

    /**
     * Updates the game.
     */
    public void update() {
        this.meter.update();
        updateBullet();
        updateEnemy();
        updatePlayer();
    }

    /**
     * Checks if the bullet has made contact, and damages the target if it does.
     * Resets currBullet to null.
     */
    private void updateBullet() {
        if (this.currBullet != null && this.currBullet.contact()) {
            damageBulletTarget();
            this.currBullet = null;
        }
    }

    /**
     * Checks if the enemy has been destroyed. If the enemy is destroyed, wait a bit, then
     * create a new enemy ship.
     */
    private void updateEnemy() {
        if (this.enemyShip.isDestroyed()) {
            if (this.currWaitingFrame >= WAITING_FRAMES) {
                this.enemyShip = shipFactory.createEnemyShip(screenWidth, screenHeight, ++this.currLevel);
                this.currWaitingFrame = 0;
            } else {
                this.currWaitingFrame++;
            }
        }
    }

    /**
     * Checks in the player has been destroyed. If the player is destroyed, wait a bit then
     * end the game.
     */
    private void updatePlayer() {
        if (this.playerShip.isDestroyed()) {
            if (this.currWaitingFrame >= WAITING_FRAMES) {
                this.stageCompleted = true;
            }
            this.currWaitingFrame++;
        }
    }

    /**
     * Damages the target of the bullet.
     */
    private void damageBulletTarget() {
        if (this.currBullet instanceof PlayerBullet) {
            damageTarget(enemyShip, playerShip);
            if(enemyShip.isDestroyed()){
                this.updateScore();
            }
        } else {
            damageTarget(playerShip, enemyShip);
        }
    }

    private void damageTarget(Ship target, Ship shooter){
        target.takeDamage(shooter.getDamage());
        if(target.getHealth() == 0){
            target.setDestroyed(true);
        }
    }

    /**
     * The action performed once the player has tapped the screen.
     */
    public void onTouch() {
        if(isAnimating()){
            return;
        }
        if (this.meter.cursorInTarget()) {
            this.currBullet = new PlayerBullet(this.screenWidth, this.screenHeight, gameStyle);
        } else {
            this.currBullet = new EnemyBullet(this.screenWidth, this.screenHeight, gameStyle);
        }

        this.meter.newTarget();
    }

    private boolean isAnimating() {
        return this.currWaitingFrame != 0 || this.currBullet != null;
    }

    /**
     * Updates the scoreboard, and sets to complete if the game has been stageCompleted.
     */
    private void updateScore() {
        this.scoreBoard.earnPoint();
        if (this.scoreBoard.getScore() % BOSS_LEVEL == 0) {
            this.stageCompleted = true;
        }
    }

    // ===== GETTERS ======
    public boolean isCompleted() {
        return this.stageCompleted;
    }

    public int getScore() {
        return this.scoreBoard.getScore();
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        drawers.addAll(meter.getDrawers());
        drawers.addAll(playerShip.getDrawers());
        drawers.addAll(enemyShip.getDrawers());
        drawers.addAll(scoreBoard.getDrawers());
        if (this.currBullet != null) {
            drawers.addAll(currBullet.getDrawers());
        }

        return drawers;
    }

    public void upgradePlayer(PowerUpSelect.PowerUps selection) {
        if(selection == PowerUpSelect.PowerUps.DAMAGE){
            this.playerShip.increaseDamage(1);
        }
        else{
            this.playerShip.setHealth(this.playerShip.getMaxHealth()+1);
        }
    }

    public void setCompleted(boolean completed) {
        this.stageCompleted = completed;
    }

    public boolean playerDestroyed(){
        return playerShip.isDestroyed();
    }

    public int getLevel() {
        return this.currLevel;
    }

    public int getPlayerHealth() {
        return this.playerShip.getHealth();
    }

    public int getPlayerDamage() {
        return this.playerShip.getDamage();
    }

    public Integer getPlayerMaxHealth() {
        return this.playerShip.getMaxHealth();
    }
}
