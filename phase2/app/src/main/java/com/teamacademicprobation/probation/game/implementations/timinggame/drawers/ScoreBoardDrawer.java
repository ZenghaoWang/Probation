package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

/**
 * An implementation of AndroidDrawer that draws the scoreboard.
 */
public class ScoreBoardDrawer implements AndroidDrawer {

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
     * The style of the scoreboard.
     */
    private Paint paint;

    /**
     * Initializes a new ScoreBoardDrawer.
     *
     * @param x     The x coordinate of the start of the text.
     * @param y     The y coordinate of the start of the text.
     * @param score The score to be displayed.
     * @param paint The style of the scoreboard.
     */
    public ScoreBoardDrawer(int x, int y, int score, Paint paint) {
        this.x = x;
        this.y = y;
        this.score = score;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText("Score: " + this.score, x, y, paint);
  }
}
