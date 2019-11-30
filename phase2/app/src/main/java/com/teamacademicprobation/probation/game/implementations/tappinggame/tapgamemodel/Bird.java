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

/**
 * A Bird object that players can tap once in the game for extra mole counts.
 */
public class Bird extends TouchableObject implements Drawable {
    /**
     * The speed of the bird which corresponds to the number of pixels it moves
     * every time the game updates
     */
    private final int speed = 200;
    /**
     * The size of the bird for rescaling the bitmap image.
     */
    private final int birdSize = 250;
    /**
     * The paint of the bird to be drawn.
     */
    private Paint paint = new Paint();
    /**
     * The bitmap of the bird to be drawn.
     */
    private Bitmap bitmap;

    /**
     * Initializes the Bird Object.
     *
     * @param context The context for retrieving resources.
     * @param x       The x coordinate of Bird in pixels.
     * @param y       The y coordinate of Bird in pixels.
     */
    public Bird(Context context, int x, int y) {
        super(x, y);
        this.setSize(birdSize);
        this.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bird));
    }


    /**
     * Sets the input bitmap as the bird object's bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = Bitmap.createScaledBitmap(bitmap, this.getSize(), this.getSize(), true);
    }

    /**
     * Moves the bird accordingly to it's speed.
     */
    public void move() {
        int newX = this.getX() - speed;
        this.setX(newX);
    }


    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer birdDrawer;
        birdDrawer = new BirdDrawer(this.bitmap, this.getX(), this.getY(), paint);
        drawers.add(birdDrawer);
        return drawers;
    }
}
