package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RedObject extends TapObject {
  private Paint paint = new Paint();

  RedObject(int x, int y) {
    super(x, y);
    paint.setColor(Color.RED);
    paint.setStyle(Paint.Style.FILL);
  }

  public void draw(Canvas canvas) {
    canvas.drawCircle(getX(), getY(), radius, paint);
  }
}
