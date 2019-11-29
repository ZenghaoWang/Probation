package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamedrawers.BirdDrawer;

import java.util.ArrayList;
import java.util.List;

public class Bird extends TouchableObject implements Drawable {
    /**
     * The paint of the mole to be drawn.
     */
    private Paint paint = new Paint();
    /**
     * The bitmap of the bole to be drawn.
     */
    private Bitmap bitmap;

    private final int speed = 200;

    /**
     * Initializes the x and y coordinates of the Mole.
     *
     * @param x The x coordinate of Mole in pixels.
     * @param y The y coordinate of Mole in pixels.
     */
    public Bird(Context context, int x, int y, int size) {
        super(x, y, size);
        this.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bird));
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


    public void move() {
        int newX = this.getX() - speed;
        this.setX(newX);
    }

    /**
     * Resize the bitmap to the desired size.
     */
    private Bitmap getResizedBitmap() {
        return Bitmap.createScaledBitmap(getBitmap(), this.getSize(), this.getSize(), true);
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer birdDrawer;
        birdDrawer = new BirdDrawer(getResizedBitmap(), this.getX(), this.getY(), paint);
        drawers.add(birdDrawer);
        return drawers;
    }
}
