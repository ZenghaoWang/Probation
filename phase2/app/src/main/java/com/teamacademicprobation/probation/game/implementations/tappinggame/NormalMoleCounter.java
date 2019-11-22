package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/** A counter for how many target objects are left in the game. */
public class NormalMoleCounter {

  /** The number of targets in the game, game ends when 30 targets appear. */
  private int normalMoleLimit = 30;
  /** The number of targets that appeared in the game */
  private int normalMoleCount;

  private Paint paint;
  /** x-coordinate for target counter */
  private int x;
  /** y-coordinate for target counter */
  private int y;

  /**
   * Initializes the x and y coordinates, normalMoleCount, paint. The x and y coordinates are in ratios
   * of the screenWidth and screenHeight.
   *
   * @param screenWidth The width of the screen in pixels.
   * @param screenHeight The height of the screen in pixels.
   */
  NormalMoleCounter(int screenWidth, int screenHeight) {
    this.normalMoleCount = 0;
    this.x = (int) (screenWidth * 0.01);
    this.y = (int) (screenHeight * 0.03);
    paint = new Paint();
    paint.setColor(Color.WHITE);
    paint.setTextSize(50);
  }

  /**
   * Returns the normalMoleCount.
   *
   * @return normalMoleCount
   */
  int getNormalMoleCount() {
    return this.normalMoleCount;
  }

  public int getNormalMoleLimit() {
    return normalMoleLimit;
  }

  /**
   * Adds a count to the normalMoleCount
   */
  void addCount() {
    this.normalMoleCount++;
  }

  /** Draws the target counter onto canvas */
  void draw(Canvas canvas) {
    canvas.drawText("Normal Moles Left:" + (normalMoleLimit - this.normalMoleCount), this.x, this.y, paint);
  }
}
