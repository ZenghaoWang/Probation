package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamedrawers;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

/**
 * An implementation of AndroidDrawer that draws the TapScoreBoard
 */
public class TapScoreBoardDrawer implements AndroidDrawer {
    /**
     * The x coordinate of the start of the text.
     */
    private int x;
    /**
     * The y coordinate of the start of the text.
     */
    private int y;
    /**
     * The score to be displayed.
     */
    private int score;
    /**
     * The paint of the scoreboard to be drawn on canvas.
     */
    private Paint paint;

    /**
     * Initializes a new TapScoreBoardDrawer.
     *
     * @param x     The x coordinate of the start of the text.
     * @param y     The y coordinate of the start of the text.
     * @param score The score to be displayed.
     * @param paint The paint of the scoreboard to be drawn on canvas.
     */
    public TapScoreBoardDrawer(int x, int y, int score, Paint paint) {
        this.x = x;
        this.y = y;
        this.score = score;
        this.paint = paint;
    }

    /**
     * Draws the TapScoreBoard on canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawText("Score: " + this.score, x, y, paint);
    }
}
