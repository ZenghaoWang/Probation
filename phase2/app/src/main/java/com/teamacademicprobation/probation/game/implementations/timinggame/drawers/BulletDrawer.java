package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * An implementation of AndroidDrawer that draws bullets.
 */

public class BulletDrawer implements AndroidDrawer {

    /**
     * The radius of this bullet.
     */
    private int radius;
    /**
     * The x coordinate of the center of the bullet.
     */
    private int x;
    /**
     * The y coordinate of the center of the bullet
     */
    private int y;
    /**
     * The style of this bullet.
     */
    private Paint paint;


    /**
     * Initializes a new BulletDrawer.
     *
     * @param radius The radius of this bullet.
     * @param x      The x coordinate of the center of the bullet.
     * @param y      The y coordinate of the center of the bullet
     * @param paint  The style of this bullet.
     */
    public BulletDrawer(int radius, int x, int y, Paint paint) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }
}
