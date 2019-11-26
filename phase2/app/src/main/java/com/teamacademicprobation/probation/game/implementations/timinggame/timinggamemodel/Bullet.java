package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.BulletDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a shot bullet.
 */

class Bullet implements Drawable {

    /**
     * The radius of the bullet.
     */
    private static final int RADIUS = 16;
    /**
     * The number of frames that the bullet should be on screen for.
     */
    static final int FRAMES = 10;
    /**
     * The current frame the bullet is on.
     */
    private int currFrame = 0;
    /**
     * How fast the bullet is moving accross the screen.
     */
    private int bulletVelocity;
    /**
     * The x coordinate of the center of the bullet.
     */
    private int x;
    /**
     * The y coordinate of the center of the bullet.
     */
    private int y;
    /**
     * The style of this bullet.
     */
    private Paint paint;


    /**
     * Initializes a new bullet.
     *
     * @param screenHeight The height of the screen, in pixels.
     * @param gameStyle    The style of the game.
     */
    Bullet(int screenHeight, TimingGameStyle gameStyle) {
        this.y = Math.toIntExact(Math.round(screenHeight * 0.25));
        generatePaint(gameStyle);
    }

    // ===== SETTERS/GETTERS =====
    void setBulletVelocity(int bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }

    void setX(int x) {
        this.x = x;
    }

    // ===== END OF SETTERS/GETTERS =====

    /**
     * Generates the paint of this bullet based on the gameStyle.
     *
     * @param gameStyle The style of the game.
     */
    private void generatePaint(TimingGameStyle gameStyle) {
        this.paint = new Paint();
        this.paint.setColor(gameStyle.getBulletColor());
    }

    /**
     * Determines if the bullet has made contact with the ship the bullet is fired at.
     *
     * @return Boolean
     */
    boolean contact() {
        return this.currFrame > FRAMES;
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        if (this.currFrame <= FRAMES) {
            AndroidDrawer drawer = new BulletDrawer(RADIUS, x, y, paint);
            this.x += bulletVelocity;
            this.currFrame += 1;
            drawers.add(drawer);
        }
        return drawers;
    }
}
