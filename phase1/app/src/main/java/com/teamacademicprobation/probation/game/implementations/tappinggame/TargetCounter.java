package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * A counter for how many target objects are left in the game.
 */
public class TargetCounter {
  /** The number of targets in the game, game ends when 30 targets appear. */
  static int targetLimit= 30;
  /** The number of targets that appeared in the game*/
  private int targetCount;
  private Paint paint;
  /** x-coordinate for target counter*/
  private int x;
  /** y-coordinate for target counter*/
  private int y;

  /**
   * Initializes the x and y coordinates, targetCount, paint. The x and y coordinates
   * are in ratios of the screenWidth and screenHeight.
   *
   * @param screenWidth The width of the screen in pixels.
   * @param screenHeight The height of the screen in pixels.
   */
  TargetCounter(int screenWidth, int screenHeight) {
    this.targetCount = 0;
    this.x = (int) (screenWidth * 0.01);
    this.y = (int) (screenHeight * 0.03);
    paint = new Paint();
    paint.setColor(Color.WHITE);
    paint.setTextSize(50);
  }

  /**
   * Returns the targetCount.
   *
   * @return targetCount
   */
  int getTargetCount() {
    return this.targetCount;
  }

  /**
   * Adds a count to the targetCount
   */
  void addCount() {
    this.targetCount++;
  }

  /**
   * Draws the target counter onto canvas
   */
  void draw(Canvas canvas) {
    canvas.drawText("Target Left:" + (targetLimit - this.targetCount), this.x, this.y, paint);
  }
}
