package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

/**
 * An implementation of AndroidDrawer that draws the damage boost image during power up selection.
 */
public class DamageBoostDrawer implements AndroidDrawer {

    /**
     * The message to display.
     */
    private static final String MESSAGE = "+1 Damage";
    /**
     * The image to display.
     */
    private Bitmap increaseDamage;
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
     * Initializes a new DamageBoostDrawer
     *
     * @param increaseDamage The image to display.
     * @param x              The left most side of the image in relation to the left of the screen.
     * @param y              The top most side of the image in relation to the top of the screen.
     * @param paint          The style of the image.
     */
    public DamageBoostDrawer(Bitmap increaseDamage, int x, int y, Paint paint) {
        this.increaseDamage = increaseDamage;
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(increaseDamage, x, y, null);
        canvas.drawText(MESSAGE, x - 20, y - 90, paint);
    }
}
