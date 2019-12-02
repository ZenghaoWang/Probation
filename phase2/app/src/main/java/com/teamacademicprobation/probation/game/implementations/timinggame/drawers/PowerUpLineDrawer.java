package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

/**
 * An implementation of AndroidDrawer that draws the line seperating the health and damage boost.
 */
public class PowerUpLineDrawer implements AndroidDrawer {

    /**
     * The x coordinate of the line.
     */
    private int x;
    /**
     * The y coordinate of the top of the line.
     */
    private int y;
    /**
     * The length of the line.
     */
    private int length;
    /**
     * The style of the line.
     */
    private Paint paint;

    /**
     * Initializes a new PowerUpLineDrawer.
     * @param x The x coordinate of the line.
     * @param y The y coordinate of the top of the line.
     * @param length The length of the line.
     * @param paint The style of the line.
     */
    public PowerUpLineDrawer(int x, int y, int length, Paint paint) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(x, y, x, y + length, paint);
    }
}
