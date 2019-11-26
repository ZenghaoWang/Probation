package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

/**
 * An implementation of AndroidDrawer that draws the spaceships.
 */
public class ShipDrawer implements AndroidDrawer {

    /**
     * The image of the spaceship to be drawn. This can either be the actual spaceship or
     * the explosion effect.
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
     * Initializes a new ShipDrawer.
     *
     * @param state The image of the spaceship to be drawn.
     * @param x     The x coordinate of the left most side of the image.
     * @param y     The y coordinate of the top most side of the image.
     * @param paint The style that defines how to draw the bitmap.
     */
    public ShipDrawer(Bitmap state, int x, int y, Paint paint) {
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
