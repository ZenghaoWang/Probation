package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import android.graphics.Color;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamedrawers.MoleCounterDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * A counter for how many NormalMole objects are left in the game.
 */
public class NormalMoleCounter implements Drawable {

    /**
     * The number of NormalMole in the game, game ends when 30 targets appear.
     */
    private int normalMoleLeft = 30;
    /**
     * The paint for the NormalMoleCounter to be drawn on canvas.
     */
    private Paint paint;
    /**
     * x-coordinate for NormalMole counter
     */
    private int x;
    /**
     * y-coordinate for NormalMole counter
     */
    private int y;


    /**
     * Initializes the NormalMoleCounter.
     *
     * @param screenWidth  The width of the screen in pixels.
     * @param screenHeight The height of the screen in pixels.
     */
    public NormalMoleCounter(int screenWidth, int screenHeight) {
        this.x = (int) (screenWidth * 0.01);
        this.y = (int) (screenHeight * 0.03);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
    }

    /**
     * @return normalMoleLeft
     */
    public int getNormalMoleLeft() {
        return normalMoleLeft;
    }

    /**
     * Reduce the normalMolesLeft by 1
     */
    public void reduceOneMole() {
        this.normalMoleLeft--;
    }

    /**
     * Sets the normalMoleLeft to a input number.
     */
    public void setLeft(int moleLeft) {
        this.normalMoleLeft = moleLeft;
    }

    /**
     * Increase the normalMoleLeft by five.
     */
    public void addFiveMoles() {
        this.normalMoleLeft = this.normalMoleLeft + 5;
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer drawer =
                new MoleCounterDrawer(
                        this.x, this.y, this.normalMoleLeft, this.paint);
        drawers.add(drawer);
        return drawers;
    }
}
