package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamedrawers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

/**
 * An implementation of AndroidDrawer that draws the background for the tapping game.
 */
public class BackGroundDrawer implements AndroidDrawer {
    /**
     * The image of the background to be drawn.
     */
    private Bitmap state;
    /**
     * The x coordinate of the left most side of the image.
     */
    private int x;
    /**
     * The y coordinate of the top most side of the image.
     */
    private int y;
    /**
     * The style that defines how to draw the bitmap.
     */
    private Paint paint;

    /**
     * Initializes a new BulletDrawer.
     *
     * @param state The image of the background.
     * @param x     The x coordinate of the background.
     * @param y     The y coordinate of the background.
     * @param paint The style of this bullet.
     */
    public BackGroundDrawer(Bitmap state, int x, int y, Paint paint) {
        this.state = state;
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(state, x, y, paint);
    }
}
