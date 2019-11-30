package com.teamacademicprobation.probation.game;

import android.graphics.Color;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;

import java.util.List;

/** An object that represents the scoreboard, to be drawn on the top right of the game. */
public abstract class ScoreBoard implements Drawable {
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


  protected int getX() {
    int charWidth = Math.toIntExact(Math.round(paint.measureText("0")));
    int numChars = ("" + score).length();
    return this.x - (charWidth * numChars);
  }

    protected int getY() {
    return y;
    }

    protected Paint getPaint() {
    return paint;
  }

  @Override
  public abstract List<AndroidDrawer> getDrawers();
}
