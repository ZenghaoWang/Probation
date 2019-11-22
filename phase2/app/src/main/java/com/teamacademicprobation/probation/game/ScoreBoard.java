package com.teamacademicprobation.probation.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/** An object that represents the scoreboard, to be drawn on the top right of the game. */
public class ScoreBoard {
  /** The current score. */
  private int score;
  /** The style of this scoreboard. */
  private Paint paint;
  /** The x coordinate of where this scoreboard should be drawn. */
  private int x;
  /** The y coordinate of where this scoreboard should be drawn. */
  private int y;

  /**
   * Initializes the scoreboard to fit on the top right of the screen.
   *
   * @param screenWidth The width of the screen, in pixels.
   * @param screenHeight The height of the screen, in pixels.
   */
  public ScoreBoard(int screenWidth, int screenHeight) {
    this.score = 0;
    // These are just nice ratios.
    this.x = (int) (screenWidth * 0.81);
    this.y = (int) (screenHeight * 0.03);
    paint = new Paint();
    paint.setColor(Color.WHITE);
    paint.setTextSize(50);
  }

  /**
   * Returns the score.
   *
   * @return int : score
   */
  public int getScore() {
    return this.score;
  }

  /**
   * Sets the score.
   *
   * @param score the score to be set.
   */
  public void setScore(int score) {
    this.score = score;
  }

  /** Increments the points by one. */
  public void earnPoint() {
    this.score++;
  }

    /**
     * Increments the points by five.
     */
    public void earnFivePoints() {
        this.score = this.score + 5;
    }

  /** Decrements the points by one. */
  public void losePoint() {
    if (score != 0) {
      this.score--;
    }
  }

  /**
   * Draws the scoreboard onto the canvas.
   *
   * @param canvas The canvas on which to draw onto.
   */
  public void draw(Canvas canvas) {
    int charWidth = Math.toIntExact(Math.round(paint.measureText("0")));
    int numChars = (int) (Math.log10(score + 1) + 1);

    canvas.drawText("Score:" + this.score, this.x - (charWidth * numChars), this.y, paint);
  }
}
