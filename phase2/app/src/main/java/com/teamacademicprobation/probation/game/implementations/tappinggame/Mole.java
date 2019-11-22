package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * A Tap object that the players will tap when playing the game.
 */
class Mole {
    /**
     * The x-coordinate TapObjects.
     */
    private int x;
    /**
     * The y-coordinateTapObjects.
     */
    private int y;

    private int size = 250;

    private Paint paint = new Paint();

    private Bitmap bitmap;


    /**
     * Initializes the x and y coordinates of the Mole.
     *
     * @param x The x coordinate of Mole in pixels.
     * @param y The y coordinate of Mole in pixels.
     */
    Mole(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x-coordinate of the Tap Object
     */
    int getX() {
        return this.x;
    }

    /**
     * @return the y-coordinate of the Tap Object
     */
    int getY() {
        return this.y;
    }

    void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    private Bitmap getBitmap() {
        return bitmap;
    }

    int getSize() {
        return size;
    }

    private Bitmap getResizedBitmap() {
        return Bitmap.createScaledBitmap(getBitmap(), this.size, this.size, true);
    }


    /**
     * Draws the Non-Target object on input canvas.
     */
    void draw(Canvas canvas) {
        canvas.drawBitmap(getResizedBitmap(), getX(), getY(), paint);
    }
}
