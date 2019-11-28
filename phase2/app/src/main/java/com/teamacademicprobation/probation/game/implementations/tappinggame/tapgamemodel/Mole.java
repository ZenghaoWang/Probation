package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import android.graphics.Bitmap;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamedrawers.MoleDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * A Mole object that the players will tap when playing the game.
 */
public class Mole implements Drawable {
    /**
     * The size of the Mole to be drawn.
     */
    private final int size = 250;
    /**
     * The x-coordinate of the Mole.
     */
    private int x;
    /**
     * The y-coordinate of the Mole.
     */
    private int y;
    /**
     * The paint of the mole to be drawn.
     */
    private Paint paint = new Paint();
    /**
     * The bitmap of the bole to be drawn.
     */
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
     * @return the x-coordinate of the Mole Object
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y-coordinate of the Mole Object
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return the bitmap of the Mole Object
     */
    private Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * Sets the input bitmap as the Mole object's bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * @return the size of the Mole
     */
    public int getSize() {
        return size;
    }

    /**
     * Resize the bitmap to the desired size.
     */
    private Bitmap getResizedBitmap() {
        return Bitmap.createScaledBitmap(getBitmap(), this.size, this.size, true);
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer moleDrawer;
        moleDrawer = new MoleDrawer(getResizedBitmap(), x, y, paint);
        drawers.add(moleDrawer);
        return drawers;
  }
}
