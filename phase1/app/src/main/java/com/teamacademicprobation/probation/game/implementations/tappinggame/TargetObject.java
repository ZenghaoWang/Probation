package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
/**
 * A Target object that when players tap, they earn a point. Non-Target object extends TapObject.
 */
class TargetObject extends TapObject {
  private Paint paint = new Paint();

  /**
   * Constructor for TargetObject,
   * @param x x-coordinate for TargetObject
   * @param y y-coordinate for TargetObject
   */
  TargetObject(int x, int y) {
    super(x, y);
    paint.setColor(Color.BLUE);
    paint.setStyle(Paint.Style.FILL);
  }

  /**
   * Draws the Target object on the input canvas.
   */
  void draw(Canvas canvas) {
    canvas.drawCircle(getX(), getY(), radius, paint);
  }
}
