package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

/**
 * An implementation of AndroidDrawer that draws the health boost image during power up selection.
 */
public class HealthBoostDrawer implements AndroidDrawer {

    /**
     * The message to display.
     */
    private static final String MESSAGE = "+1 Health";
    /**
     * The image to display.
     */
    private Bitmap increaseHealth;
    /**
     * The left most side of the image in relation to the left of the screen.
     */
    private int x;
    /**
     * The top most side of the image in relation to the top of the screen.
     */
    private int y;
    /**
     * The style of the image.
     */
    private Paint paint;

    /**
     * Initializes a new HealthBoostDrawer.
     *
     * @param increaseHealth The image to display.
     * @param x              The left most side of the image in relation to the left of the screen.
     * @param y              The top most side of the image in relation to the top of the screen.
     * @param paint          The style of the image.
     */
    public HealthBoostDrawer(Bitmap increaseHealth, int x, int y, Paint paint) {
        this.increaseHealth = increaseHealth;
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(increaseHealth, x, y, null);
        canvas.drawText(MESSAGE, x, y - 90, paint);
    }
}
