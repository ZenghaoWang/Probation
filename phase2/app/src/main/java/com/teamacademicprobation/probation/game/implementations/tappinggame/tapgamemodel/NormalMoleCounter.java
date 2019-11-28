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
    private final int normalMoleLimit = 30;
    /**
     * The number of NormalMole that appeared in the game
     */
    private int normalMoleCount;

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
     * Initializes the x and y coordinates, normalMoleCount, paint. The x and y coordinates are in
     * ratios of the screenWidth and screenHeight.
     *
     * @param screenWidth  The width of the screen in pixels.
     * @param screenHeight The height of the screen in pixels.
     */
    public NormalMoleCounter(int screenWidth, int screenHeight) {
        this.normalMoleCount = 0;
        this.x = (int) (screenWidth * 0.01);
        this.y = (int) (screenHeight * 0.03);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
    }

    /**
     * Returns the normalMoleCount.
     *
     * @return normalMoleCount
     */
    public int getNormalMoleCount() {
        return this.normalMoleCount;
    }

    /**
     * Returns the normalMoleLimit.
     *
     * @return normalMoleCount
     */
    public int getNormalMoleLimit() {
        return normalMoleLimit;
    }

    /**
     * Adds a count to the normalMoleCount
     */
    public void addCount() {
        this.normalMoleCount++;
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer drawer =
                new MoleCounterDrawer(
                        this.x, this.y, this.getNormalMoleLimit(), this.getNormalMoleCount(), this.paint);
        drawers.add(drawer);
        return drawers;
  }
}
