package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * A Non-Target object that when players tap, they lose a point. Non-Target object extends
 * TapObject.
 */
class NonTargetObject extends TapObject {
  private Paint paint = new Paint();

  /**
   * Initializes the paint and x and y coordinates of the non-target Object.
   * @param x The x coordinate of TapObject in pixels.
   * @param y The y coordinate of TapObject in pixels.
   */
  NonTargetObject(int x, int y) {
    super(x, y);
    paint.setColor(Color.RED);
    paint.setStyle(Paint.Style.FILL);
  }

  /**
   * Draws the Non-Target object on input canvas.
   */
  void draw(Canvas canvas) {
    canvas.drawCircle(getX(), getY(), radius, paint);
  }
}
